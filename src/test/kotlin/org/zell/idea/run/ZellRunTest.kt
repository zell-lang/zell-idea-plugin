package org.zell.idea.run

import com.intellij.execution.PsiLocation
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.psi.PsiElement
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.zell.idea.psi.ZellTypes

/** Gutter markers, configuration producers and the command line of Zell run configurations. */
class ZellRunTest : BasePlatformTestCase() {
    private val source = """
        namespace demo

        func helper() int {
            return 1
        }

        @test func TestAdd(t *testing.T) {
            testing.Equal(t, 1 + 1, 2)
        }

        @test
        func Plain() {
        }

        struct S {
            func main() {
            }
        }

        func main() {
            println(helper())
        }
    """.trimIndent()

    private fun nameLeaf(name: String, occurrence: Int = 0): PsiElement {
        val text = myFixture.file.text
        var at = -1
        repeat(occurrence + 1) { at = text.indexOf("func $name", at + 1) }
        assertTrue("func $name not found", at >= 0)
        val leaf = myFixture.file.findElementAt(at + "func ".length)!!
        assertEquals(ZellTypes.IDENTIFIER, leaf.node.elementType)
        return leaf
    }

    fun testLineMarkers() {
        myFixture.configureByText("demo_test.zl", source)
        val contributor = ZellRunLineMarkerContributor()
        assertNotNull(contributor.getInfo(nameLeaf("TestAdd")))
        assertNotNull(contributor.getInfo(nameLeaf("Plain")))
        assertNotNull(contributor.getInfo(nameLeaf("main", 1)))
        assertNull("method main is not an entry point", contributor.getInfo(nameLeaf("main", 0)))
        assertNull(contributor.getInfo(nameLeaf("helper")))
    }

    fun testTestProducer() {
        myFixture.configureByText("demo_test.zl", source)
        val context = ConfigurationContext.createEmptyContextForLocation(PsiLocation(nameLeaf("TestAdd")))
        val fromContext = ZellTestProducer().createConfigurationFromContext(context)!!
        val config = fromContext.configuration as ZellRunConfiguration
        assertEquals(ZellCommand.TEST, config.command)
        assertEquals("^TestAdd$", config.testFilter)
        assertEquals("TestAdd", config.name)
        assertTrue(config.target.endsWith("demo_test.zl"))
        assertTrue(ZellTestProducer().isConfigurationFromContext(config, context))

        val args = config.commandLine(java.nio.file.Paths.get("/opt/zell/bin/zell")).parametersList.list
        assertEquals(listOf("test", config.target, "--filter", "^TestAdd$", "--json"), args)
    }

    fun testRunProducer() {
        myFixture.configureByText("demo_test.zl", source)
        val inMain = myFixture.file.findElementAt(myFixture.file.text.indexOf("println"))!!
        val context = ConfigurationContext.createEmptyContextForLocation(PsiLocation(inMain))
        val config = ZellRunProducer().createConfigurationFromContext(context)!!.configuration as ZellRunConfiguration
        assertEquals(ZellCommand.RUN, config.command)
        config.programArguments = "a \"b c\""
        config.compilerOptions = "--release"
        val args = config.commandLine(java.nio.file.Paths.get("/opt/zell/bin/zell")).parametersList.list
        assertEquals(listOf("run", "--release", config.target, "--", "a", "b c"), args)

        // Not from inside a test function.
        val inTest = ConfigurationContext.createEmptyContextForLocation(PsiLocation(nameLeaf("TestAdd")))
        assertNull(ZellRunProducer().createConfigurationFromContext(inTest))
    }

    fun testTestLocator() {
        myFixture.configureByText("demo_test.zl", source)
        val found = ZellTestLocator.getLocation("zell", "test/TestAdd", project, com.intellij.psi.search.GlobalSearchScope.allScope(project))
        assertEquals(1, found.size)
        assertTrue(ZellTestLocator.getLocation("zell", "test/helper", project, com.intellij.psi.search.GlobalSearchScope.allScope(project)).isEmpty())
    }
}
