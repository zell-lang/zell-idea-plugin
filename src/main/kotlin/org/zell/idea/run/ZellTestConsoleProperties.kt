package org.zell.idea.run

import com.intellij.execution.Executor
import com.intellij.execution.testframework.TestConsoleProperties
import com.intellij.execution.testframework.sm.SMCustomMessagesParsing
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties
import com.intellij.execution.testframework.sm.runner.SMTestLocator

/** Test tree for `zell test --json`. */
class ZellTestConsoleProperties(configuration: ZellRunConfiguration, executor: Executor) :
    SMTRunnerConsoleProperties(configuration, "Zell", executor), SMCustomMessagesParsing {

    init {
        isIdBasedTestTree = false
        setIfUndefined(TestConsoleProperties.HIDE_PASSED_TESTS, false)
    }

    override fun createTestEventsConverter(
        testFrameworkName: String,
        consoleProperties: TestConsoleProperties,
    ): OutputToGeneralTestEventsConverter = ZellTestEventsConverter(testFrameworkName, consoleProperties)

    override fun getTestLocator(): SMTestLocator = ZellTestLocator
}
