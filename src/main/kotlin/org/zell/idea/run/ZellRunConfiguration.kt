package org.zell.idea.run

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configuration.EnvironmentVariablesData
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RuntimeConfigurationError
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.util.execution.ParametersListUtil
import org.zell.idea.toolchain.ZellToolchain
import java.nio.file.Path
import java.nio.file.Paths

/**
 * `zell run|build|test [compiler options] [target] [--filter re] [-- args]`
 * with the compiler from the toolchain settings.
 */
class ZellRunConfiguration(project: Project, factory: ConfigurationFactory, name: String) :
    LocatableConfigurationBase<ZellRunConfigurationOptions>(project, factory, name) {

    public override fun getOptions(): ZellRunConfigurationOptions = super.getOptions() as ZellRunConfigurationOptions

    var command: ZellCommand
        get() = ZellCommand.entries.firstOrNull { it.name == options.command } ?: ZellCommand.RUN
        set(value) {
            options.command = value.name
        }

    var target: String
        get() = options.target.orEmpty()
        set(value) {
            options.target = value
        }

    var compilerOptions: String
        get() = options.compilerOptions.orEmpty()
        set(value) {
            options.compilerOptions = value
        }

    var programArguments: String
        get() = options.programArguments.orEmpty()
        set(value) {
            options.programArguments = value
        }

    var testFilter: String
        get() = options.testFilter.orEmpty()
        set(value) {
            options.testFilter = value
        }

    var workingDirectory: String
        get() = options.workingDirectory.orEmpty()
        set(value) {
            options.workingDirectory = value
        }

    var env: EnvironmentVariablesData
        get() = EnvironmentVariablesData.create(options.env, options.passParentEnv)
        set(value) {
            options.env = LinkedHashMap(value.envs)
            options.passParentEnv = value.isPassParentEnvs
        }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> = ZellRunConfigurationEditor(project)

    override fun checkConfiguration() {
        if (ZellToolchain.zellExecutable() == null) {
            throw RuntimeConfigurationError("The zell compiler was not found (Settings | Languages & Frameworks | Zell)")
        }
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState =
        ZellRunState(this, environment)

    override fun suggestedName(): String? {
        val t = target.takeIf { it.isNotBlank() }?.let { Paths.get(it).fileName?.toString() } ?: return null
        return when (command) {
            ZellCommand.TEST -> if (testFilter.isNotBlank()) testName(testFilter) ?: "test $t" else "test $t"
            ZellCommand.BUILD -> "build $t"
            ZellCommand.RUN -> t
        }
    }

    /** The working directory: the setting, else the project root. */
    fun workDir(): Path? =
        workingDirectory.takeIf { it.isNotBlank() }?.let { Paths.get(it) } ?: project.basePath?.let { Paths.get(it) }

    /** The full command line, without the environment. */
    fun commandLine(zell: Path): GeneralCommandLine {
        val args = mutableListOf(command.cli)
        args += ParametersListUtil.parse(compilerOptions)
        if (target.isNotBlank()) args += target
        if (command == ZellCommand.TEST) {
            if (testFilter.isNotBlank()) args += listOf("--filter", testFilter)
            args += "--json"
        }
        if (command == ZellCommand.RUN) {
            val program = ParametersListUtil.parse(programArguments)
            if (program.isNotEmpty()) args += listOf("--") + program
        }
        val line = GeneralCommandLine(listOf(zell.toString()) + args).withCharset(Charsets.UTF_8)
        workDir()?.let { line.withWorkDirectory(it.toFile()) }
        env.configureCommandLine(line, true)
        return line
    }

    fun commandLine(): GeneralCommandLine =
        commandLine(ZellToolchain.zellExecutable() ?: throw ExecutionException("The zell compiler was not found"))

    companion object {
        /** `^Name$` (what the producers write) -> `Name`. */
        fun testName(filter: String): String? =
            Regex("""\^([A-Za-z_][A-Za-z0-9_]*)\$""").matchEntire(filter)?.groupValues?.get(1)

        fun filterFor(testName: String): String = "^$testName$"
    }
}
