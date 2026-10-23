package org.zell.idea.run

import com.intellij.execution.DefaultExecutionResult
import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.process.KillableColoredProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.testframework.sm.SMTestRunnerConnectionUtil
import com.intellij.execution.ui.ConsoleView

/**
 * Starts `zell`; file links in the output come from [ZellConsoleFilter.Provider].
 * `zell test` output goes to the test tree (`--json` events), the rest to a console.
 */
class ZellRunState(private val configuration: ZellRunConfiguration, environment: ExecutionEnvironment) :
    CommandLineState(environment) {

    override fun startProcess(): ProcessHandler {
        val handler = KillableColoredProcessHandler(configuration.commandLine())
        ProcessTerminatedListener.attach(handler)
        return handler
    }

    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
        if (configuration.command != ZellCommand.TEST) return super.execute(executor, runner)
        val handler = startProcess()
        val properties = ZellTestConsoleProperties(configuration, executor)
        val console: ConsoleView = SMTestRunnerConnectionUtil.createAndAttachConsole("Zell", handler, properties)
        return DefaultExecutionResult(console, handler, *createActions(console, handler, executor))
    }
}
