package org.zell.idea.run

import com.intellij.execution.configuration.EnvironmentVariablesComponent
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import javax.swing.DefaultComboBoxModel
import javax.swing.JComponent

/** Settings of a Zell run configuration. */
class ZellRunConfigurationEditor(project: Project) : SettingsEditor<ZellRunConfiguration>() {
    private val command = ComboBox(DefaultComboBoxModel(ZellCommand.entries.toTypedArray())).apply {
        renderer = com.intellij.ui.SimpleListCellRenderer.create("") { it.cli }
    }
    private val target = TextFieldWithBrowseButton().apply {
        addBrowseFolderListener(
            "Target", "A .zl file or a package directory", project,
            FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor(),
        )
    }
    private val compilerOptions = JBTextField()
    private val programArguments = JBTextField()
    private val testFilter = JBTextField()
    private val workingDirectory = TextFieldWithBrowseButton().apply {
        addBrowseFolderListener(
            "Working Directory", null, project,
            FileChooserDescriptorFactory.createSingleFolderDescriptor(),
        )
    }
    private val env = EnvironmentVariablesComponent()

    override fun resetEditorFrom(s: ZellRunConfiguration) {
        command.selectedItem = s.command
        target.text = s.target
        compilerOptions.text = s.compilerOptions
        programArguments.text = s.programArguments
        testFilter.text = s.testFilter
        workingDirectory.text = s.workingDirectory
        env.envData = s.env
    }

    override fun applyEditorTo(s: ZellRunConfiguration) {
        s.command = command.selectedItem as? ZellCommand ?: ZellCommand.RUN
        s.target = target.text.trim()
        s.compilerOptions = compilerOptions.text.trim()
        s.programArguments = programArguments.text.trim()
        s.testFilter = testFilter.text.trim()
        s.workingDirectory = workingDirectory.text.trim()
        s.env = env.envData
    }

    override fun createEditor(): JComponent = panel {
        row("Command:") { cell(command) }
        row("Target:") { cell(target).align(AlignX.FILL).comment("A .zl file or a package directory; empty: the package of the working directory") }
        row("Compiler options:") { cell(compilerOptions).align(AlignX.FILL).comment("For example --release") }
        row("Program arguments:") { cell(programArguments).align(AlignX.FILL).comment("zell run only, passed after --") }
        row("Test filter:") { cell(testFilter).align(AlignX.FILL).comment("zell test only: --filter regular expression") }
        row("Working directory:") { cell(workingDirectory).align(AlignX.FILL) }
        row { cell(env).align(AlignX.FILL) }
    }
}
