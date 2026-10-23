package org.zell.idea.toolchain

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.util.ExecUtil
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import java.nio.file.Path
import javax.swing.JLabel

/** Settings | Languages & Frameworks | Zell. */
class ZellToolchainConfigurable : BoundConfigurable("Zell") {
    private val state get() = ZellToolchainSettings.getInstance().state

    override fun createPanel(): DialogPanel = panel {
        group("Toolchain") {
            row("zell:") {
                textFieldWithBrowseButton("Select the zell Executable", null, FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor())
                    .bindText(state::zellPath)
                    .align(AlignX.FILL)
                    .comment("Empty: detect on PATH, then ~/.zell/toolchain/current/bin. Detected: ${ZellToolchain.zellExecutable() ?: "not found"}")
            }
            row("zell-lsp:") {
                textFieldWithBrowseButton("Select the zell-lsp Executable", null, FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor())
                    .bindText(state::lspPath)
                    .align(AlignX.FILL)
                    .comment("Empty: detect on PATH or next to zell; without it the IDE runs `zell lsp`. Detected: ${ZellToolchain.lspExecutable() ?: "not found"}")
            }
            row("ZELL_HOME:") {
                textFieldWithBrowseButton("Select ZELL_HOME", null, FileChooserDescriptorFactory.createSingleFolderDescriptor())
                    .bindText(state::zellHome)
                    .align(AlignX.FILL)
                    .comment("The standard library comes from `zell env ZELL_STD`, else ZELL_HOME/std. Install: scripts/install.sh from the zell repository.")
            }
        }
        group("Detected") {
            row("zell:") { cell(zellVersion) }
            row("zell-lsp:") { cell(lspVersion) }
            row {
                button("Refresh") { refreshVersions() }
                browserLink("Install the toolchain…", ZellToolchainActions.INSTALL_URL)
            }
        }
        refreshVersions()
    }

    private val zellVersion = JLabel()
    private val lspVersion = JLabel()

    /** Runs `zell --version` and `zell-lsp --version` off the EDT and shows the result. */
    private fun refreshVersions() {
        zellVersion.text = "…"
        lspVersion.text = "…"
        ApplicationManager.getApplication().executeOnPooledThread {
            val zell = describe(ZellToolchain.zellExecutable())
            val lsp = describe(ZellToolchain.lspExecutable())
            ApplicationManager.getApplication().invokeLater({
                zellVersion.text = zell
                lspVersion.text = lsp
            }, ModalityState.any())
        }
    }

    override fun apply() {
        super.apply()
        ZellToolchain.toolchainChanged()
        refreshVersions()
    }

    companion object {
        /** `zell 0.1.0 (x86_64) — /path/to/zell`, or why it is unavailable. */
        fun describe(exe: Path?): String {
            exe ?: return "not found"
            return try {
                val out = ExecUtil.execAndGetOutput(GeneralCommandLine(exe.toString(), "--version"), 5_000)
                val version = out.stdout.lineSequence().firstOrNull { it.isNotBlank() }?.trim()
                if (out.exitCode == 0 && version != null) "$version — $exe" else "$exe (--version failed)"
            } catch (e: Exception) {
                "$exe (${e.message})"
            }
        }
    }
}
