package org.zell.idea.run

import com.intellij.testFramework.fixtures.BasePlatformTestCase

/** File links in console output: diagnostics, error traces, test reports. */
class ZellConsoleFilterTest : BasePlatformTestCase() {
    private fun links(line: String): List<Triple<String, String, Int?>> {
        val filter = ZellConsoleFilter(project, null)
        val prefix = "previous output\n"
        val result = filter.applyFilter(line, prefix.length + line.length) ?: return emptyList()
        return result.resultItems.map {
            val info = it.hyperlinkInfo as ZellFileHyperlinkInfo
            val text = line.substring(it.highlightStartOffset - prefix.length, it.highlightEndOffset - prefix.length)
            Triple(text, "${info.path}:${info.line}", info.column)
        }
    }

    fun testPatterns() {
        assertEquals(listOf(Triple("errors_trace.zl:24", "errors_trace.zl:24", null)), links("  errors_trace.zl:24 Port"))
        assertEquals(listOf(Triple("src/main.zl:3:5", "src/main.zl:3", 5)), links("src/main.zl:3:5: error: undefined: x"))
        assertEquals(listOf(Triple("/home/u/p/a_test.zl:12", "/home/u/p/a_test.zl:12", null)), links("--- FAIL: TestX (0.00s)  /home/u/p/a_test.zl:12"))
        assertEquals(listOf(Triple("app/db.zl:7", "app/db.zl:7", null)), links("  created at   app/db.zl:7"))
        assertEquals(listOf(Triple("app/db.zl:9", "app/db.zl:9", null)), links("  returned via app/db.zl:9"))
        assertEquals(emptyList<Any>(), links("listening on 8080"))
        assertEquals(emptyList<Any>(), links("main.go:3"))
    }

    fun testResolvesByUniqueName() {
        val file = myFixture.addFileToProject("pkg/errors_trace.zl", "namespace pkg\n").virtualFile
        val info = ZellFileHyperlinkInfo(project, "errors_trace.zl", 1, null, null)
        assertEquals(file, info.resolve())
    }
}
