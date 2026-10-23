package org.zell.idea

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.intellij.execution.util.ExecUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.zell.idea.lsp.ZellLspServerDescriptor
import org.zell.idea.templates.ZellNewProjectWizard
import org.zell.idea.toolchain.ZellToolchainSettings
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.concurrent.TimeUnit

/**
 * Runs the real toolchain from `-Dzell.toolchain` (Gradle: env ZELL_TOOLCHAIN,
 * a directory with `zell` and `zell-lsp`); every test is skipped with a
 * message when it is not set.
 */
class ZellToolchainIntegrationTest : BasePlatformTestCase() {
    private fun toolchain(): File? {
        val dir = System.getProperty("zell.toolchain")?.let(::File)
        if (dir == null || !File(dir, "zell").canExecute()) {
            println("SKIPPED ${name}: set ZELL_TOOLCHAIN to a directory with zell and zell-lsp")
            return null
        }
        return dir
    }

    override fun setUp() {
        super.setUp()
        val dir = System.getProperty("zell.toolchain") ?: return
        ZellToolchainSettings.getInstance().state.zellPath = File(dir, "zell").path
        ZellToolchainSettings.getInstance().state.lspPath = File(dir, "zell-lsp").path
    }

    override fun tearDown() {
        try {
            ZellToolchainSettings.getInstance().loadState(ZellToolchainSettings.State())
        } finally {
            super.tearDown()
        }
    }

    /** What the New Project wizard runs: `zell init <dir> --name n`. */
    fun testZellInit() {
        val dir = toolchain() ?: return
        val target = Files.createTempDirectory("zell-init").resolve("my-app")
        val command = ZellNewProjectWizard.initCommand(target, "my-app", false, "toml", File(dir, "zell").toPath())!!
        Files.createDirectories(target)
        val out = ExecUtil.execAndGetOutput(command, 30_000)
        assertEquals(out.stderr, 0, out.exitCode)
        val main = target.resolve("src/main.zl")
        assertTrue(Files.isRegularFile(main))
        assertTrue(Files.readString(main).startsWith("namespace my_app"))
        assertTrue(Files.isRegularFile(target.resolve("zell.toml")))
    }

    /** zell-lsp answers `initialize` with our initialization options and the capabilities the plugin relies on. */
    fun testLspInitialize() {
        toolchain() ?: return
        val descriptor = ZellLspServerDescriptor(project)
        val process = descriptor.createCommandLine().createProcess()
        try {
            val params = JsonObject().apply {
                addProperty("processId", ProcessHandle.current().pid())
                addProperty("rootUri", File(project.basePath ?: ".").toURI().toString())
                add("capabilities", JsonObject())
                add("initializationOptions", com.google.gson.Gson().toJsonTree(descriptor.createInitializationOptions()))
            }
            val request = JsonObject().apply {
                addProperty("jsonrpc", "2.0")
                addProperty("id", 1)
                addProperty("method", "initialize")
                add("params", params)
            }.toString().toByteArray(StandardCharsets.UTF_8)
            process.outputStream.write("Content-Length: ${request.size}\r\n\r\n".toByteArray(StandardCharsets.US_ASCII))
            process.outputStream.write(request)
            process.outputStream.flush()

            val input = process.inputStream
            val header = StringBuilder()
            while (!header.endsWith("\r\n\r\n")) {
                val b = input.read()
                assertTrue("zell-lsp closed stdout", b >= 0)
                header.append(b.toChar())
            }
            val length = Regex("Content-Length: (\\d+)").find(header)!!.groupValues[1].toInt()
            val body = String(input.readNBytes(length), StandardCharsets.UTF_8)
            val caps = JsonParser.parseString(body).asJsonObject.getAsJsonObject("result").getAsJsonObject("capabilities")
            for (cap in listOf("hoverProvider", "definitionProvider", "completionProvider", "documentFormattingProvider", "referencesProvider", "codeActionProvider")) {
                assertTrue("missing $cap in $caps", caps.has(cap))
            }
            assertFalse("push diagnostics requested, no pull provider expected", caps.has("diagnosticProvider"))
        } finally {
            process.destroy()
            process.waitFor(5, TimeUnit.SECONDS)
        }
    }
}
