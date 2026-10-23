package org.zell.idea.templates

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.util.ExecUtil
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.GeneratorNewProjectWizard
import com.intellij.ide.wizard.GitNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardBaseData.Companion.baseData
import com.intellij.ide.wizard.NewProjectWizardBaseStep
import com.intellij.ide.wizard.NewProjectWizardChainStep.Companion.nextStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.ide.wizard.RootNewProjectWizardStep
import com.intellij.ide.wizard.setupProjectFromBuilder
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.module.GeneralModuleType
import com.intellij.openapi.module.ModuleTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.bindItem
import com.intellij.ui.dsl.builder.bindSelected
import org.zell.idea.lang.ZellIcons
import org.zell.idea.toolchain.ZellToolchain
import java.nio.file.Path

/**
 * File | New | Project | Zell: name, location and Git from the platform
 * steps, then `zell init <dir> --name <name> [--lib] [--format f]` with the
 * toolchain's compiler, and the generated entry file opened.
 */
class ZellNewProjectWizard : GeneratorNewProjectWizard {
    override val id: String = "Zell"
    override val name: String = "Zell"
    override val icon = ZellIcons.FILE
    override val description: String = "A Zell package created by `zell init`"

    override fun createStep(context: WizardContext): NewProjectWizardStep =
        RootNewProjectWizardStep(context)
            .nextStep(::NewProjectWizardBaseStep)
            .nextStep(::GitNewProjectWizardStep)
            .nextStep(::ZellInitStep)

    class ZellInitStep(parent: NewProjectWizardStep) : AbstractNewProjectWizardStep(parent) {
        private val libraryProperty = propertyGraph.property(false)
        private val formatProperty = propertyGraph.property("toml")

        override fun setupUI(builder: Panel) {
            builder.row("Manifest format:") {
                comboBox(listOf("toml", "json", "yaml")).bindItem(formatProperty)
            }
            builder.row {
                checkBox("Library (src/lib.zl instead of src/main.zl)").bindSelected(libraryProperty)
            }
            if (ZellToolchain.zellExecutable() == null) {
                builder.row {
                    comment("The zell compiler was not found: set it in Settings | Languages &amp; Frameworks | Zell.")
                }
            }
        }

        override fun setupProject(project: Project) {
            val base = baseData ?: return
            val dir = java.nio.file.Paths.get(base.path, base.name)
            // A module with the project folder as its content root, like the platform's empty project.
            val builder = ModuleTypeManager.getInstance().findByID(GeneralModuleType.TYPE_ID).createModuleBuilder()
            builder.name = base.name
            builder.contentEntryPath = dir.toString()
            builder.moduleFilePath = dir.resolve(base.name + ".iml").toString()
            setupProjectFromBuilder(project, builder)

            val command = initCommand(dir, base.name, libraryProperty.get(), formatProperty.get()) ?: run {
                notify(project, "The zell compiler was not found; the project was created without `zell init`.")
                return
            }
            ApplicationManager.getApplication().executeOnPooledThread {
                val out = try {
                    ExecUtil.execAndGetOutput(command, 30_000)
                } catch (e: Exception) {
                    notify(project, "`${command.commandLineString}` failed: ${e.message}")
                    return@executeOnPooledThread
                }
                if (out.exitCode != 0) {
                    notify(project, "`${command.commandLineString}` failed:\n${out.stderr.ifBlank { out.stdout }}")
                    return@executeOnPooledThread
                }
                val entry = dir.resolve(if (libraryProperty.get()) "src/lib.zl" else "src/main.zl")
                val vf = LocalFileSystem.getInstance().refreshAndFindFileByNioFile(entry)
                LocalFileSystem.getInstance().refreshAndFindFileByNioFile(dir)?.refresh(false, true)
                if (vf != null) {
                    ApplicationManager.getApplication().invokeLater {
                        if (!project.isDisposed) FileEditorManager.getInstance(project).openFile(vf, true)
                    }
                }
            }
        }

        private fun notify(project: Project, text: String) {
            NotificationGroupManager.getInstance().getNotificationGroup("Zell")
                .createNotification(text, NotificationType.WARNING).notify(project)
        }
    }

    companion object {
        /** `zell init <dir> --name <name> [--lib] [--format f]`, or null without a compiler. */
        fun initCommand(dir: Path, name: String, library: Boolean, format: String, zell: Path? = ZellToolchain.zellExecutable()): GeneralCommandLine? {
            zell ?: return null
            val args = mutableListOf(zell.toString(), "init", dir.toString(), "--name", name)
            if (library) args += "--lib"
            if (format != "toml") args += listOf("--format", format)
            return GeneralCommandLine(args).withWorkDirectory(dir.toFile()).withCharset(Charsets.UTF_8)
        }
    }
}
