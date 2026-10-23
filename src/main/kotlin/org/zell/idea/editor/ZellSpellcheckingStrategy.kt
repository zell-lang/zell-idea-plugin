package org.zell.idea.editor

import com.intellij.psi.PsiElement
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import org.zell.idea.psi.ZellTokenSets

/**
 * Spellchecker scopes: comments and declaration names (the platform default
 * for `PsiComment` and `PsiNameIdentifierOwner`) plus string, raw string and
 * char literals.
 */
class ZellSpellcheckingStrategy : SpellcheckingStrategy() {
    override fun getTokenizer(element: PsiElement): Tokenizer<*> {
        if (element.node?.elementType in ZellTokenSets.STRINGS) return TEXT_TOKENIZER
        return super.getTokenizer(element)
    }
}
