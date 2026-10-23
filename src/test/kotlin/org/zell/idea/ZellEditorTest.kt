package org.zell.idea

import com.intellij.psi.search.PsiTodoSearchHelper
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.spellchecker.inspections.SpellCheckingInspection
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.zell.idea.editor.ZellBreadcrumbsProvider
import org.zell.idea.psi.impl.ZellDeclMixin

/** Typing, Enter indentation, TODO items, spellchecking and breadcrumbs. */
class ZellEditorTest : BasePlatformTestCase() {
    fun testQuoteAutoClose() {
        myFixture.configureByText("a.zl", "namespace a\n\nvar s = <caret>\n")
        myFixture.type("\"")
        myFixture.checkResult("namespace a\n\nvar s = \"<caret>\"\n")
    }

    fun testRawStringAutoClose() {
        myFixture.configureByText("a.zl", "namespace a\n\nvar s = <caret>\n")
        myFixture.type("`")
        myFixture.checkResult("namespace a\n\nvar s = `<caret>`\n")
    }

    fun testEnterBetweenBracesIndents() {
        myFixture.configureByText("a.zl", "namespace a\n\nfunc main() {<caret>}\n")
        myFixture.type("\n")
        myFixture.checkResult("namespace a\n\nfunc main() {\n    <caret>\n}\n")
    }

    fun testEnterInNestedBlock() {
        myFixture.configureByText("a.zl", "namespace a\n\nfunc main() {\n    if true {<caret>\n    }\n}\n")
        myFixture.type("\n")
        myFixture.checkResult("namespace a\n\nfunc main() {\n    if true {\n        <caret>\n    }\n}\n")
    }

    fun testEnterAfterStatementKeepsIndent() {
        myFixture.configureByText("a.zl", "namespace a\n\nfunc main() {\n    println(1)<caret>\n}\n")
        myFixture.type("\n")
        myFixture.checkResult("namespace a\n\nfunc main() {\n    println(1)\n    <caret>\n}\n")
    }

    fun testTodoItems() {
        val file = myFixture.configureByText("a.zl", "namespace a\n\n// TODO handle errors\n/* FIXME later */\nfunc main() {\n}\n")
        assertEquals(2, PsiTodoSearchHelper.getInstance(project).findTodoItems(file).size)
    }

    fun testSpellcheckingCommentsStringsAndNames() {
        myFixture.enableInspections(SpellCheckingInspection())
        myFixture.configureByText(
            "a.zl",
            "namespace a\n\n// a <TYPO>mispeled</TYPO> word\nfunc <TYPO>Wrold</TYPO>() string {\n    return \"<TYPO>helo</TYPO> there\"\n}\n",
        )
        myFixture.checkHighlighting(false, false, true)
    }

    fun testBreadcrumbs() {
        myFixture.configureByText("a.zl", "namespace a\n\nstruct Shape {\n    func Area() float {\n        return 1.0<caret>\n    }\n}\n")
        val provider = ZellBreadcrumbsProvider()
        val at = myFixture.file.findElementAt(myFixture.caretOffset)!!
        val crumbs = generateSequence(at) { it.parent }.filter(provider::acceptElement).map(provider::getElementInfo).toList().reversed()
        assertEquals(listOf("Shape", "Area()"), crumbs)
        assertNotNull(PsiTreeUtil.getParentOfType(at, ZellDeclMixin::class.java)?.let(provider::getElementIcon))
    }
}
