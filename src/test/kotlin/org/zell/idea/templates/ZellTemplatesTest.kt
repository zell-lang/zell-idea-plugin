package org.zell.idea.templates

import com.intellij.codeInsight.template.impl.InvokeTemplateAction
import com.intellij.codeInsight.template.impl.TemplateManagerImpl
import com.intellij.codeInsight.template.impl.TemplateSettings
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.testFramework.fixtures.BasePlatformTestCase

/** Live templates in the Zell context and the namespace of new files. */
class ZellTemplatesTest : BasePlatformTestCase() {
    fun testTestTemplate() {
        myFixture.configureByText("a_test.zl", "namespace demo\n\n<caret>\n")
        myFixture.type("test")
        myFixture.performEditorAction(IdeActions.ACTION_EXPAND_LIVE_TEMPLATE_BY_TAB)
        // NAME stops first with its default "Name"; finish the template.
        TemplateManagerImpl.getTemplateState(myFixture.editor)?.gotoEnd(false)
        assertEquals(
            "namespace demo\n\n@test func TestName(t *testing.T) {\n    \n}\n",
            myFixture.editor.document.text,
        )
    }

    fun testNotInComments() {
        myFixture.configureByText("a.zl", "namespace demo\n\n// <caret>\n")
        myFixture.type("main")
        myFixture.performEditorAction(IdeActions.ACTION_EXPAND_LIVE_TEMPLATE_BY_TAB)
        assertFalse(myFixture.editor.document.text.contains("func main"))
    }

    fun testNamespaceForNewFile() {
        val file = myFixture.addFileToProject("app/models/user.zl", "namespace app.models\n")
        assertEquals("app.models", ZellCreateFileAction.namespaceFor(file.containingDirectory))
        val empty = myFixture.tempDirFixture.findOrCreateDir("my-tool")
        assertEquals("my_tool", ZellCreateFileAction.namespaceFor(psiManager.findDirectory(empty)!!))
    }

    fun testTestFileName() {
        assertEquals("Parser", ZellCreateFileAction.testName("parser_test"))
        assertEquals("HttpClient", ZellCreateFileAction.testName("http_client_test"))
        assertEquals("HttpClient", ZellCreateFileAction.testName("http-client"))
    }

    fun testFileTemplateKinds() {
        val manager = FileTemplateManager.getInstance(project)
        assertTrue(manager.getInternalTemplate(ZellCreateFileAction.MAIN_TEMPLATE).text.contains("func main()"))
        assertTrue(manager.getInternalTemplate(ZellCreateFileAction.TEST_TEMPLATE).text.contains("@test func Test\${TEST_NAME}(t *testing.T)"))
    }

    fun testSurroundWithTry() {
        myFixture.configureByText("a.zl", "namespace demo\n\nfunc main() {\n    <selection>work()</selection>\n}\n")
        val template = TemplateSettings.getInstance().getTemplate("try", "Zell")!!
        InvokeTemplateAction(template, myFixture.editor, project, HashSet()).perform()
        assertEquals(
            "namespace demo\n\nfunc main() {\n    try {\n        work()\n    } catch err {\n        \n    }\n}\n",
            myFixture.editor.document.text,
        )
    }

    fun testPostfixVar() {
        myFixture.configureByText("a.zl", "namespace demo\n\nfunc main() {\n    compute(1).var<caret>\n}\n")
        myFixture.type("\t")
        TemplateManagerImpl.getTemplateState(myFixture.editor)?.gotoEnd(false)
        assertEquals("namespace demo\n\nfunc main() {\n    var v = compute(1)\n}\n", myFixture.editor.document.text)
    }

    fun testPostfixReturn() {
        myFixture.configureByText("a.zl", "namespace demo\n\nfunc f() int {\n    a + b.return<caret>\n}\n")
        myFixture.type("\t")
        assertEquals("namespace demo\n\nfunc f() int {\n    return a + b\n}\n", myFixture.editor.document.text)
    }
}
