package org.zell.idea.run

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.zell.idea.psi.ZellTypes

/** PSI shapes the run support recognizes (syntax only, no resolve). */
object ZellRunUtil {
    /** A top-level `func` (not a method: methods sit inside `struct_member`). */
    fun isTopLevelFunc(e: PsiElement?): Boolean =
        e != null && e.node.elementType == ZellTypes.FUNC_DECL && e.parent?.node?.elementType == ZellTypes.TOP_LEVEL_DECL

    /** `func main()` / `async func main()` at the top level. */
    fun isMainFunction(e: PsiElement?): Boolean =
        isTopLevelFunc(e) && funcName(e!!) == "main"

    /**
     * A function marked `@test` (upstream docs/guide/testing.md: `@test func
     * TestX(t *testing.T)` or `@test func X()`; `@test` with `*testing.B` is a
     * benchmark, still run by name).
     */
    fun isTestFunction(e: PsiElement?): Boolean =
        isTopLevelFunc(e) && attributeNames(e!!.parent).any { it == "test" }

    fun funcName(func: PsiElement): String? =
        func.node.findChildByType(ZellTypes.FUNC_SIGNATURE)?.findChildByType(ZellTypes.IDENTIFIER)?.text

    /** The function whose name token is [leaf], or null. */
    fun funcOfNameLeaf(leaf: PsiElement): PsiElement? {
        if (leaf.node.elementType != ZellTypes.IDENTIFIER) return null
        val sig = leaf.parent ?: return null
        if (sig.node.elementType != ZellTypes.FUNC_SIGNATURE) return null
        val func = sig.parent ?: return null
        return func.takeIf { funcName(it) != null && sig.node.findChildByType(ZellTypes.IDENTIFIER) == leaf.node }
    }

    /** The enclosing top-level function of [e], or null. */
    fun enclosingTopLevelFunc(e: PsiElement): PsiElement? {
        var cur: PsiElement? = e
        while (cur != null) {
            if (isTopLevelFunc(cur)) return cur
            cur = cur.parent
        }
        return null
    }

    private fun attributeNames(topLevel: PsiElement): List<String> =
        PsiTreeUtil.getChildrenOfAnyType(topLevel, PsiElement::class.java)
            .filter { it.node.elementType == ZellTypes.ATTRIBUTE }
            .mapNotNull { it.node.findChildByType(ZellTypes.QUALIFIED_NAME)?.text }
}
