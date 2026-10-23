package org.zell.idea.editor

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import org.zell.idea.psi.ZellTokenSets

/** Auto-closes `"strings"`, `'c'` chars and `` `raw strings` `` (one token each in the lexer). */
class ZellQuoteHandler : SimpleTokenSetQuoteHandler(ZellTokenSets.STRINGS)
