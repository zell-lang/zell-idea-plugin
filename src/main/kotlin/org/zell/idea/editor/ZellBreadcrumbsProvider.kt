package org.zell.idea.editor

import com.intellij.icons.AllIcons
import com.intellij.lang.Language
import com.intellij.psi.PsiElement
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
import org.zell.idea.lang.ZellLanguage
import org.zell.idea.psi.ZellTypes
import org.zell.idea.psi.impl.ZellDeclMixin
import javax.swing.Icon

/** Breadcrumbs: `Shape › Area()` for the struct, interface, enum, function or method around the caret. */
class ZellBreadcrumbsProvider : BreadcrumbsProvider {
    override fun getLanguages(): Array<Language> = arrayOf(ZellLanguage)

    override fun acceptElement(element: PsiElement): Boolean = element is ZellDeclMixin && element.name != null

    override fun getElementInfo(element: PsiElement): String {
        val decl = element as ZellDeclMixin
        return if (decl.node.elementType == ZellTypes.FUNC_DECL) "${decl.name}()" else decl.name.orEmpty()
    }

    override fun getElementIcon(element: PsiElement): Icon? {
        val decl = element as? ZellDeclMixin ?: return null
        if (decl.node.elementType == ZellTypes.FUNC_DECL && decl.node.treeParent?.elementType != ZellTypes.TOP_LEVEL_DECL) {
            return AllIcons.Nodes.Method
        }
        return decl.kind.icon
    }
}
