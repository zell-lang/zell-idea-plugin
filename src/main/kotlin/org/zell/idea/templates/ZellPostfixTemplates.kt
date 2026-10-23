package org.zell.idea.templates

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TextExpression
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplate
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateExpressionSelector
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateProvider
import com.intellij.codeInsight.template.postfix.templates.StringBasedPostfixTemplate
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.Function
import org.zell.idea.psi.ZellExpr
import org.zell.idea.psi.ZellExprStmt

/** Postfix templates on an expression statement: `f(x).var`, `f(x).return`. */
class ZellPostfixTemplateProvider : PostfixTemplateProvider {
    private val templates: Set<PostfixTemplate> = setOf(
        ZellPostfixTemplate("var", "var name = expr", "var \$name\$ = \$expr\$\$END\$", this, withName = true),
        ZellPostfixTemplate("return", "return expr", "return \$expr\$\$END\$", this, withName = false),
    )

    override fun getTemplates(): Set<PostfixTemplate> = templates

    override fun isTerminalSymbol(currentChar: Char): Boolean = currentChar == '.'

    override fun preExpand(file: PsiFile, editor: Editor) {}

    override fun afterExpand(file: PsiFile, editor: Editor) {}

    override fun preCheck(copyFile: PsiFile, realEditor: Editor, currentOffset: Int): PsiFile = copyFile
}

private class ZellPostfixTemplate(
    name: String,
    example: String,
    private val text: String,
    provider: PostfixTemplateProvider,
    private val withName: Boolean,
) : StringBasedPostfixTemplate(name, example, StatementExpressionSelector, provider) {
    override fun getTemplateString(element: PsiElement): String = text

    override fun setVariables(template: Template, element: PsiElement) {
        if (withName) template.addVariable("name", TextExpression("v"), true)
    }

    /** Replace the whole expression statement (its zero-width `;` included). */
    override fun getElementToRemove(expr: PsiElement): PsiElement = expr.parent as? ZellExprStmt ?: expr
}

/** The expression of the expression statement that ends at the caret (the key `.var` already removed). */
private object StatementExpressionSelector : PostfixTemplateExpressionSelector {
    override fun hasExpression(context: PsiElement, copyDocument: Document, newOffset: Int): Boolean =
        find(context, newOffset) != null

    override fun getExpressions(context: PsiElement, document: Document, offset: Int): List<PsiElement> =
        listOfNotNull(find(context, offset))

    override fun getRenderer(): Function<PsiElement, String> = Function { it.text }

    private fun find(context: PsiElement, offset: Int): PsiElement? {
        val stmt = generateSequence(context) { it.parent }.takeWhile { it !is PsiFile }
            .firstOrNull { it is ZellExprStmt } ?: return null
        val expr = stmt.children.firstOrNull { it is ZellExpr } ?: return null
        return expr.takeIf { it.textRange.endOffset == offset }
    }
}
