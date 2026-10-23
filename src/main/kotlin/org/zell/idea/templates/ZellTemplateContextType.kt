package org.zell.idea.templates

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.psi.PsiComment
import com.intellij.psi.util.PsiUtilCore
import org.zell.idea.lang.ZellFile
import org.zell.idea.psi.ZellTokenSets

/** Live template context "Zell": anywhere in a `.zl` file except comments and strings. */
class ZellTemplateContextType : TemplateContextType("Zell") {
    override fun isInContext(context: TemplateActionContext): Boolean {
        val file = context.file as? ZellFile ?: return false
        val at = file.findElementAt(context.startOffset) ?: return true
        if (at is PsiComment) return false
        return PsiUtilCore.getElementType(at) !in ZellTokenSets.STRINGS
    }
}
