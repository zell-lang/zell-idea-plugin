package org.zell.idea.run

import junit.framework.TestCase

/** `zell test --json` events -> service messages (format of upstream compiler/src/testrun.rs). */
class ZellTestEventsConverterTest : TestCase() {
    private fun t(line: String) = ZellTestEventsConverter.translate(line)

    fun testEvents() {
        assertEquals(
            listOf("##teamcity[testStarted name='TestOk' locationHint='zell://test/TestOk']"),
            t("""{"Action":"run","Test":"TestOk"}"""),
        )
        assertEquals(listOf("##teamcity[testFinished name='TestOk' duration='4']"), t("""{"Action":"pass","Test":"TestOk","Elapsed":0.004}"""))
        assertEquals(
            listOf("##teamcity[testStdOut name='TestBad' out='    got 2, want 3|n']"),
            t("""{"Action":"output","Test":"TestBad","Output":"    got 2, want 3\n"}"""),
        )
        assertEquals(
            listOf("##teamcity[testFailed name='TestBad' message='']", "##teamcity[testFinished name='TestBad' duration='1']"),
            t("""{"Action":"fail","Test":"TestBad","Elapsed":0.001}"""),
        )
        assertEquals(emptyList<String>(), t("""{"Action":"fail","Elapsed":0.003}"""))
        assertNull(t("demo_test.zl:3:5: error: undefined: x"))
        assertNull(t("{not json}"))
    }
}
