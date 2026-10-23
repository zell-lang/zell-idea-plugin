package org.zell.idea.run

import java.nio.file.Files
import java.nio.file.Path

/** Package lookup shared by run configurations and the LSP code-lens commands. */
object ZellPackages {
    /** Package manifests (upstream compiler `package/manifest.rs`, editors/vscode `packageDir`). */
    val MANIFESTS = listOf("zell.toml", "zell.json", "zell.yaml", "zell.yml")

    /** The nearest directory at or above [start] that holds a zell manifest, or null. */
    fun packageDir(start: Path?): Path? {
        var dir: Path? = start
        while (dir != null) {
            val d: Path = dir
            if (MANIFESTS.any { Files.isRegularFile(d.resolve(it)) }) return d
            dir = d.parent
        }
        return null
    }

    /** What `zell run/build/test` should get for [file]: its package directory, else the file itself. */
    fun target(file: Path): Path = packageDir(file.parent) ?: file
}
