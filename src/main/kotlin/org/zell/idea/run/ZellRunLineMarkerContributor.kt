package org.zell.idea.run

import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

/** Gutter Run (and Debug, when a debugger runner exists) on `func main` and on `@test` functions. */
class ZellRunLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        val func = ZellRunUtil.funcOfNameLeaf(element) ?: return null
        val icon = when {
            ZellRunUtil.isMainFunction(func) -> AllIcons.RunConfigurations.TestState.Run
            ZellRunUtil.isTestFunction(func) -> AllIcons.RunConfigurations.TestState.Run
            else -> return null
        }
        return Info(icon, ExecutorAction.getActions(0)) { "Run ${ZellRunUtil.funcName(func)}" }
    }
}
