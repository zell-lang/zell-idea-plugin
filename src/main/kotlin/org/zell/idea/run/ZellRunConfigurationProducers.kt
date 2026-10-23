package org.zell.idea.run

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import org.zell.idea.lang.ZellFile
import java.nio.file.Path
import java.nio.file.Paths

/** Base of the producers: the `.zl` file of the context and its `zell` target. */
abstract class ZellProducerBase : LazyRunConfigurationProducer<ZellRunConfiguration>() {
    override fun getConfigurationFactory(): ConfigurationFactory = ZellRunConfigurationType.getInstance().factory

    protected fun filePath(context: ConfigurationContext): Path? {
        val file = context.psiLocation?.containingFile as? ZellFile ?: return null
        // `path`, not toNioPath(): also works for the in-memory files of light tests.
        return file.virtualFile?.path?.let { Paths.get(it) }
    }
}

/**
 * `zell run` from `func main` (or anywhere in a file that has one, e.g. the
 * editor's Run action): the file's package, else the file.
 */
class ZellRunProducer : ZellProducerBase() {
    override fun setupConfigurationFromContext(
        configuration: ZellRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>,
    ): Boolean {
        val location = context.psiLocation ?: return false
        val main = mainOf(location) ?: return false
        val path = filePath(context) ?: return false
        val target = ZellPackages.target(path)
        configuration.command = ZellCommand.RUN
        configuration.target = target.toString()
        configuration.workingDirectory = (if (target == path) path.parent else target).toString()
        configuration.name = configuration.suggestedName() ?: "zell run"
        sourceElement.set(main)
        return true
    }

    override fun isConfigurationFromContext(configuration: ZellRunConfiguration, context: ConfigurationContext): Boolean {
        val location = context.psiLocation ?: return false
        if (mainOf(location) == null) return false
        val path = filePath(context) ?: return false
        return configuration.command == ZellCommand.RUN && configuration.target == ZellPackages.target(path).toString()
    }

    private fun mainOf(e: PsiElement): PsiElement? {
        ZellRunUtil.enclosingTopLevelFunc(e)?.let { return it.takeIf(ZellRunUtil::isMainFunction) }
        val file = e.containingFile as? ZellFile ?: return null
        return file.children.asSequence().flatMap { it.children.asSequence() }.firstOrNull(ZellRunUtil::isMainFunction)
    }
}

/** `zell test --filter ^Name$` from a `@test` function, or `zell test` of the package from a `*_test.zl` file. */
class ZellTestProducer : ZellProducerBase() {
    override fun setupConfigurationFromContext(
        configuration: ZellRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>,
    ): Boolean {
        val location = context.psiLocation ?: return false
        val path = filePath(context) ?: return false
        val test = ZellRunUtil.enclosingTopLevelFunc(location)?.takeIf(ZellRunUtil::isTestFunction)
        val target = ZellPackages.target(path)
        configuration.command = ZellCommand.TEST
        configuration.target = target.toString()
        configuration.workingDirectory = (if (target == path) path.parent else target).toString()
        if (test != null) {
            val name = ZellRunUtil.funcName(test) ?: return false
            configuration.testFilter = ZellRunConfiguration.filterFor(name)
            configuration.name = name
            sourceElement.set(test)
        } else {
            if (!path.fileName.toString().endsWith("_test.zl")) return false
            configuration.testFilter = ""
            configuration.name = "test ${target.fileName}"
            sourceElement.set(location.containingFile)
        }
        return true
    }

    override fun isConfigurationFromContext(configuration: ZellRunConfiguration, context: ConfigurationContext): Boolean {
        val location = context.psiLocation ?: return false
        val path = filePath(context) ?: return false
        if (configuration.command != ZellCommand.TEST || configuration.target != ZellPackages.target(path).toString()) return false
        val test = ZellRunUtil.enclosingTopLevelFunc(location)?.takeIf(ZellRunUtil::isTestFunction)
        val name = test?.let(ZellRunUtil::funcName)
        return if (name != null) configuration.testFilter == ZellRunConfiguration.filterFor(name) else configuration.testFilter.isEmpty()
    }
}
