package org.zell.idea

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.ParsingTestCase
import org.zell.idea.parser.ZellParserDefinition

/** Parser cases for the 2026-10-23 language changes: leading dot, soft `in`, type aliases, keyword field names. */
class ZellParserFeaturesTest : ParsingTestCase("", "zl", ZellParserDefinition()) {

    override fun getTestDataPath(): String = "src/test/testData"

    private fun parse(body: String): String {
        val text = "namespace app\n\n$body\n"
        val psi = createFile("t.zl", text)
        val errors = PsiTreeUtil.collectElementsOfType(psi, PsiErrorElement::class.java)
        assertTrue("parse errors in <$body>: ${errors.map { it.errorDescription }}", errors.isEmpty())
        return toParseTreeText(psi, true, false)
    }

    fun testLeadingDot() {
        val tree = parse("func main() {\n    var s = items\n        // comment\n\n        .filter(f)\n        .len()\n}")
        assertFalse(tree.contains("PsiErrorElement"))
    }

    fun testInIdentifier() {
        val tree = parse("func main() {\n    var in = 1\n    for i, v in xs {\n        println(in, i, v)\n    }\n}")
        assertTrue(tree.contains("ZellForInImpl") || tree.contains("for_in"))
    }

    fun testTypeAlias() {
        val tree = parse("type Celsius = float64\ntype Names = []string\nfunc f() {}")
        assertTrue(tree.contains("TypeAliasDecl"))
    }

    fun testKeywordField() {
        parse(
            "struct Conf {\n    enum string\n    default string\n    if int\n}\n" +
                "func f() {\n    var c = Conf{enum: \"a\", default: \"b\", if: 1}\n    g(enum: 1)\n}\n" +
                "@Conf(enum: \"a,b\", default: \"x\")\nstruct S {}",
        )
    }
}
