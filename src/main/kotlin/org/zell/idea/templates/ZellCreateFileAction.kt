package org.zell.idea.templates

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import org.zell.idea.lang.ZellFile
import org.zell.idea.lang.ZellIcons
import org.zell.idea.run.ZellPackages
import java.nio.file.Files

/**
 * File | New | Zell File with three kinds: a plain file (`namespace <ns>`, plus
 * `func main` for `main.zl`), a main program and a test file (`<name>_test.zl`
 * with an `@test` function).
 */
class ZellCreateFileAction : CreateFileFromTemplateAction("Zell File", "Create a Zell source file", ZellIcons.FILE), DumbAware {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("New Zell File")
            .addKind("Zell file", ZellIcons.FILE, TEMPLATE)
            .addKind("Main program", ZellIcons.FILE, MAIN_TEMPLATE)
            .addKind("Test file", ZellIcons.FILE, TEST_TEMPLATE)
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String =
        "Create Zell file $newName"

    override fun createFileFromTemplate(name: String, template: FileTemplate, dir: PsiDirectory): PsiFile? {
        val props = FileTemplateManager.getInstance(dir.project).defaultProperties
        props.setProperty("NAMESPACE", namespaceFor(dir))
        var fileName = name.removeSuffix(".zl")
        if (template.name == TEST_TEMPLATE) {
            if (!fileName.endsWith("_test")) fileName += "_test"
            props.setProperty("TEST_NAME", testName(fileName))
        }
        return FileTemplateUtil.createFromTemplate(template, fileName, props, dir) as? PsiFile
    }

    companion object {
        const val TEMPLATE = "Zell File"
        const val MAIN_TEMPLATE = "Zell Main"
        const val TEST_TEMPLATE = "Zell Test"

        /** `TestParser` for `parser_test`, `TestHttpClient` for `http_client_test`. */
        fun testName(fileName: String): String {
            val words = fileName.removeSuffix("_test").split('_', '-', '.').filter { it.isNotEmpty() }
            val name = words.joinToString("") { w -> w.replaceFirstChar { it.uppercaseChar() } }
            return name.ifEmpty { "Name" }.let { if (it[0].isDigit()) "_$it" else it }
        }

        /**
         * The namespace of a sibling `.zl` file, else the package's root
         * namespace (`namespace = "..."` in the manifest), else the folder name.
         */
        fun namespaceFor(dir: PsiDirectory): String {
            dir.files.asSequence().filterIsInstance<ZellFile>().firstNotNullOfOrNull { it.namespaceName }?.let { return it }
            val path = java.nio.file.Paths.get(dir.virtualFile.path)
            ZellPackages.packageDir(path)?.let { pkg ->
                ZellPackages.MANIFESTS.map(pkg::resolve).filter(Files::isRegularFile).forEach { m ->
                    NAMESPACE_IN_MANIFEST.find(Files.readString(m))?.let { return it.groupValues[1] }
                }
            }
            return sanitize(dir.name)
        }

        /** `namespace = "x"` (toml), `"namespace": "x"` (json), `namespace: x` (yaml). */
        private val NAMESPACE_IN_MANIFEST = Regex("""(?m)^\s*"?namespace"?\s*[=:]\s*"?([A-Za-z_][A-Za-z0-9_.]*)""")

        fun sanitize(name: String): String {
            val s = name.map { if (it.isLetterOrDigit() || it == '_') it else '_' }.joinToString("")
            return if (s.isEmpty() || s[0].isDigit()) "_$s" else s
        }
    }
}
