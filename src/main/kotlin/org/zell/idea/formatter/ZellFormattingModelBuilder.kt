package org.zell.idea.formatter

import com.intellij.formatting.Block
import com.intellij.formatting.ChildAttributes
import com.intellij.formatting.FormattingContext
import com.intellij.formatting.FormattingModel
import com.intellij.formatting.FormattingModelBuilder
import com.intellij.formatting.FormattingModelProvider
import com.intellij.formatting.Indent
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.tree.TokenSet
import org.zell.idea.psi.ZellTypes

/**
 * Indentation only: one level (4 spaces, as `zell fmt`) inside `{}`, `()` and
 * `[]`, never any spacing changes. It drives the indent after Enter, auto-indent
 * of pasted lines and Code › Auto-Indent Lines. Code › Reformat goes to
 * `zell fmt` ([ZellFormattingService]; zell-lsp formatting in commercial IDEs),
 * which are registered before the platform's core formatter.
 */
class ZellFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val file = formattingContext.containingFile
        return FormattingModelProvider.createFormattingModelForPsiFile(
            file, ZellBlock(file.node, Indent.getNoneIndent()), formattingContext.codeStyleSettings,
        )
    }
}

private class ZellBlock(node: ASTNode, private val indent: Indent) : AbstractBlock(node, null, null) {
    override fun buildChildren(): List<Block> {
        val blocks = ArrayList<Block>()
        var depth = 0
        var child = myNode.firstChildNode
        while (child != null) {
            val type = child.elementType
            if (type in CLOSE) depth--
            // Skip whitespace and the zero-width automatic semicolons.
            if (type != TokenType.WHITE_SPACE && child.textLength > 0) {
                blocks.add(ZellBlock(child, if (depth > 0) Indent.getNormalIndent() else Indent.getNoneIndent()))
            }
            if (type in OPEN) depth++
            child = child.treeNext
        }
        return blocks
    }

    override fun getIndent(): Indent = indent

    override fun getSpacing(child1: Block?, child2: Block): Spacing? = null

    override fun isLeaf(): Boolean = myNode.firstChildNode == null

    /** A line inserted after an unclosed `{`, `(` or `[` of this node is indented one level. */
    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        var depth = 0
        for (block in subBlocks.take(newChildIndex)) {
            val type = (block as ZellBlock).node.elementType
            if (type in OPEN) depth++ else if (type in CLOSE) depth--
        }
        return ChildAttributes(if (depth > 0) Indent.getNormalIndent() else Indent.getNoneIndent(), null)
    }

    private companion object {
        val OPEN = TokenSet.create(ZellTypes.LBRACE, ZellTypes.LPAREN, ZellTypes.LBRACKET)
        val CLOSE = TokenSet.create(ZellTypes.RBRACE, ZellTypes.RPAREN, ZellTypes.RBRACKET)
    }
}
