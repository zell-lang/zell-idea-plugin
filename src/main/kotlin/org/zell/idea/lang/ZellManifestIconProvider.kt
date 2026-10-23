package org.zell.idea.lang

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.zell.idea.run.ZellPackages
import javax.swing.Icon

/**
 * Gives package manifests (`zell.toml`, `zell.json`, `zell.yaml`) the Zell
 * manifest icon while keeping their TOML/JSON/YAML file types and editors.
 */
class ZellManifestIconProvider : FileIconProvider {
    override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? =
        if (!file.isDirectory && file.name in ZellPackages.MANIFESTS) ZellIcons.MANIFEST else null
}
