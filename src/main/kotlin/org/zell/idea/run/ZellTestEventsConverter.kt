package org.zell.idea.run

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.intellij.execution.testframework.TestConsoleProperties
import com.intellij.execution.testframework.sm.ServiceMessageBuilder
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter
import com.intellij.openapi.util.Key
import jetbrains.buildServer.messages.serviceMessages.ServiceMessageVisitor

/**
 * Turns `zell test --json` lines (Go test2json style, upstream
 * compiler/src/testrun.rs: `{"Action":"run|output|pass|fail|skip",
 * "Test":name, "Output":text, "Elapsed":seconds}`) into the platform's
 * service messages. Anything else (compiler diagnostics) stays console text.
 */
class ZellTestEventsConverter(testFrameworkName: String, consoleProperties: TestConsoleProperties) :
    OutputToGeneralTestEventsConverter(testFrameworkName, consoleProperties) {

    override fun processServiceMessages(text: String, outputType: Key<*>, visitor: ServiceMessageVisitor): Boolean {
        val messages = translate(text) ?: return super.processServiceMessages(text, outputType, visitor)
        for (m in messages) super.processServiceMessages(m, outputType, visitor)
        return true
    }

    companion object {
        /** Service messages for one output line, or null if it is not a test event. */
        fun translate(line: String): List<String>? {
            val trimmed = line.trim()
            if (!trimmed.startsWith("{") || !trimmed.endsWith("}")) return null
            val o: JsonObject = try {
                JsonParser.parseString(trimmed).takeIf { it.isJsonObject }?.asJsonObject ?: return null
            } catch (e: Exception) {
                return null
            }
            val action = o.str("Action") ?: return null
            val test = o.str("Test")
                // The summary event (`{"Action":"fail","Elapsed":..}`) has no test: nothing to show.
                ?: return emptyList()
            val millis = o.get("Elapsed")?.takeIf { it.isJsonPrimitive }?.asDouble?.let { (it * 1000).toLong() }
            fun finished() = ServiceMessageBuilder.testFinished(test).apply {
                if (millis != null) addAttribute("duration", millis.toString())
            }.toString()
            return when (action) {
                "run" -> listOf(
                    ServiceMessageBuilder.testStarted(test)
                        .addAttribute("locationHint", ZellTestLocator.url(test)).toString(),
                )
                "output" -> listOf(
                    ServiceMessageBuilder.testStdOut(test).addAttribute("out", o.str("Output").orEmpty()).toString(),
                )
                "pass" -> listOf(finished())
                "fail" -> listOf(
                    ServiceMessageBuilder.testFailed(test).addAttribute("message", "").toString(),
                    finished(),
                )
                "skip" -> listOf(
                    ServiceMessageBuilder.testIgnored(test).addAttribute("message", o.str("Output").orEmpty()).toString(),
                    finished(),
                )
                else -> null
            }
        }

        private fun JsonObject.str(name: String): String? =
            get(name)?.takeIf { it.isJsonPrimitive }?.asString
    }
}
