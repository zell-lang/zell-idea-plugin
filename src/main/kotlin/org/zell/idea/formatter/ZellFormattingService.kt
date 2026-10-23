package org.zell.idea.formatter

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.CapturingProcessHandler
import com.intellij.formatting.service.AsyncDocumentFormattingService
import com.intellij.formatting.service.AsyncFormattingRequest
import com.intellij.formatting.service.FormattingService
import com.intellij.psi.PsiFile
import org.zell.idea.lang.ZellFile
import org.zell.idea.toolchain.ZellToolchain
import java.nio.charset.StandardCharsets

/**
 * Code › Reformat Code (and Actions on Save › Reformat code) with
 * `zell fmt --stdin`, in every IDE. The whole file is formatted, as `zell
 * fmt` has no range mode; the result is the same as zell-lsp formatting.
 */
class ZellFormattingService : AsyncDocumentFormattingService() {
    override fun getFeatures(): Set<FormattingService.Feature> = emptySet()

    override fun canFormat(file: PsiFile): Boolean = file is ZellFile && ZellToolchain.zellExecutable() != null

    override fun getNotificationGroupId(): String = "Zell"

    override fun getName(): String = "zell fmt"

    override fun createFormattingTask(request: AsyncFormattingRequest): FormattingTask? {
        val zell = ZellToolchain.zellExecutable() ?: return null
        val commandLine = GeneralCommandLine(zell.toString(), "fmt", "--stdin").withCharset(StandardCharsets.UTF_8)
        request.ioFile?.parentFile?.let(commandLine::withWorkDirectory)
        val handler = try {
            CapturingProcessHandler(commandLine)
        } catch (e: Exception) {
            request.onError("zell fmt", e.message ?: e.toString())
            return null
        }
        return object : FormattingTask {
            override fun run() {
                handler.processInput.use { it.write(request.documentText.toByteArray(StandardCharsets.UTF_8)) }
                val out = handler.runProcess(TIMEOUT_MS)
                when {
                    out.isTimeout -> request.onError("zell fmt", "zell fmt timed out")
                    out.exitCode != 0 -> request.onError("zell fmt", out.stderr.ifBlank { "exit code ${out.exitCode}" })
                    else -> request.onTextReady(out.stdout)
                }
            }

            override fun cancel(): Boolean {
                handler.destroyProcess()
                return true
            }

            override fun isRunUnderProgress(): Boolean = true
        }
    }

    private companion object {
        const val TIMEOUT_MS = 30_000
    }
}
