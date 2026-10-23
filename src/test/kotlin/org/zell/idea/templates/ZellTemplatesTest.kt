package org.zell.idea.templates

import com.intellij.codeInsight.template.impl.TemplateManagerImpl
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
}
