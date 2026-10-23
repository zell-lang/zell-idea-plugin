package org.zell.idea.run

import com.intellij.execution.filters.ConsoleFilterProvider
import com.intellij.execution.filters.Filter
import com.intellij.execution.filters.HyperlinkInfo
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Hyperlinks for `path.zl:LINE` and `path.zl:LINE:COL` in console output:
 * compiler diagnostics (`file.zl:3:5: error: ...`), error traces
 * (`  errors_trace.zl:24 Port`, upstream examples/errors_trace.out; also
 * `created at   path:line` / `returned via path:line`), panics and test
 * reports (`--- FAIL: X (0.00s)  file.zl:12`).
 *
 * The file is resolved when the link is clicked: absolute, else against
 * [workDir] and the project root, else by file name if it is unique in the project.
 */
class ZellConsoleFilter(private val project: Project, private val workDir: Path?) : Filter {
    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        val start = entireLength - line.length
        val items = PATTERN.findAll(line).map { m ->
            val path = m.groups[1]!!.value
            val lineNo = m.groups[2]!!.value.toInt()
            val col = m.groups[3]?.value?.toInt()
            Filter.ResultItem(
                start + m.range.first,
                start + m.range.last + 1,
                ZellFileHyperlinkInfo(project, path, lineNo, col, workDir),
            )
        }.toList()
        return if (items.isEmpty()) null else Filter.Result(items)
    }

    companion object {
        /** A path ending in `.zl` (no spaces or `:` inside), `:LINE`, optional `:COL`. */
        val PATTERN = Regex("""((?:[A-Za-z]:)?[^\s:"'(\[]*\.zl):(\d+)(?::(\d+))?""")
    }

    /** Registers the filter for every console of the project (also terminals of other run configurations). */
    class Provider : ConsoleFilterProvider {
        override fun getDefaultFilters(project: Project): Array<Filter> =
            arrayOf(ZellConsoleFilter(project, project.basePath?.let { Paths.get(it) }))
    }
}

/** Opens `path:line[:col]` (1-based in the text, 0-based in the descriptor). */
class ZellFileHyperlinkInfo(
    private val project: Project,
    val path: String,
    val line: Int,
    val column: Int?,
    private val workDir: Path?,
) : HyperlinkInfo {
    override fun navigate(project: Project) {
        val file = resolve() ?: return
        OpenFileDescriptor(project, file, (line - 1).coerceAtLeast(0), ((column ?: 1) - 1).coerceAtLeast(0)).navigate(true)
    }

    fun resolve(): VirtualFile? {
        val fs = LocalFileSystem.getInstance()
        val p = try {
            Paths.get(path)
        } catch (e: Exception) {
            return null
        }
        if (p.isAbsolute) return fs.refreshAndFindFileByNioFile(p)
        val bases = listOfNotNull(workDir, project.basePath?.let { Paths.get(it) })
        for (base in bases) {
            fs.refreshAndFindFileByNioFile(base.resolve(p).normalize())?.let { return it }
        }
        val name = p.fileName?.toString() ?: return null
        val candidates = FilenameIndex.getVirtualFilesByName(name, GlobalSearchScope.allScope(project))
            .filter { it.path.endsWith(path.replace('\\', '/')) }
        return candidates.singleOrNull()
    }
}
