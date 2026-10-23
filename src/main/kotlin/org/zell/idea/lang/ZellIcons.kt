package org.zell.idea.lang

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/** Plugin icons; the `_dark.svg` variants are picked automatically in dark themes. */
object ZellIcons {
    /** `.zl` files, the run configuration type, the New Project wizard and the LSP status bar item. */
    @JvmField
    val FILE: Icon = IconLoader.getIcon("/icons/zell.svg", ZellIcons::class.java)

    /** Package manifests: `zell.toml`, `zell.json`, `zell.yaml`. */
    @JvmField
    val MANIFEST: Icon = IconLoader.getIcon("/icons/zellManifest.svg", ZellIcons::class.java)
}
