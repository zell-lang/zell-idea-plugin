package org.zell.idea.editor

import com.intellij.lexer.Lexer
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.cache.impl.BaseFilterLexer
import com.intellij.psi.impl.cache.impl.OccurrenceConsumer
import com.intellij.psi.impl.cache.impl.todo.LexerBasedTodoIndexer
import com.intellij.psi.impl.search.IndexPatternBuilder
import com.intellij.psi.search.UsageSearchContext
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.zell.idea.lang.ZellFile
import org.zell.idea.lexer.ZellHighlightingLexer
import org.zell.idea.psi.ZellTokenSets
import org.zell.idea.psi.ZellTokenTypes

/** `// TODO` and `/* FIXME */` patterns for the TODO tool window and editor highlighting. */
class ZellIndexPatternBuilder : IndexPatternBuilder {
    override fun getIndexingLexer(file: PsiFile): Lexer? = if (file is ZellFile) ZellHighlightingLexer() else null

    override fun getCommentTokenSet(file: PsiFile): TokenSet? = if (file is ZellFile) ZellTokenSets.COMMENTS else null

    override fun getCommentStartDelta(tokenType: IElementType?): Int = 0

    override fun getCommentEndDelta(tokenType: IElementType?): Int = if (tokenType == ZellTokenTypes.BLOCK_COMMENT) 2 else 0
}

/** Counts TODO items in comments for the TODO index. */
class ZellTodoIndexer : LexerBasedTodoIndexer() {
    override fun createLexer(consumer: OccurrenceConsumer): Lexer = FilterLexer(consumer)

    private class FilterLexer(consumer: OccurrenceConsumer) : BaseFilterLexer(ZellHighlightingLexer(), consumer) {
        override fun advance() {
            if (myDelegate.tokenType in ZellTokenSets.COMMENTS) {
                scanWordsInToken(UsageSearchContext.IN_COMMENTS.toInt(), false, false)
                advanceTodoItemCountsInToken()
            }
            myDelegate.advance()
        }
    }
}
