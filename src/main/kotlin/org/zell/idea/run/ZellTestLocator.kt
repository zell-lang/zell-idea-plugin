package org.zell.idea.run

import com.intellij.execution.Location
import com.intellij.execution.PsiLocation
import com.intellij.execution.testframework.sm.runner.SMTestLocator
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import org.zell.idea.psi.ZellNamedElement
import org.zell.idea.stubs.ZellDeclNameIndex

/**
 * `zell://test/<Name>` -> the top-level function `Name` (the stub index of
 * top-level declarations; `zell test` reports bare function names).
 */
object ZellTestLocator : SMTestLocator {
    const val PROTOCOL = "zell"

    fun url(test: String): String = "$PROTOCOL://test/$test"

    override fun getLocation(
        protocol: String,
        path: String,
        project: Project,
        scope: GlobalSearchScope,
    ): List<Location<*>> {
        if (protocol != PROTOCOL || !path.startsWith("test/")) return emptyList()
        val name = path.removePrefix("test/")
        return StubIndex.getElements(ZellDeclNameIndex.KEY, name, project, scope, ZellNamedElement::class.java)
            .filter { ZellRunUtil.isTestFunction(it) }
            .map { PsiLocation(project, it) }
    }
}
