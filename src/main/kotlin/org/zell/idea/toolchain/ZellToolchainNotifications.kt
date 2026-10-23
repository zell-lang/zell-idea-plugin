package org.zell.idea.toolchain

import com.intellij.ide.BrowserUtil
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.EditorNotificationPanel
import com.intellij.ui.EditorNotificationProvider
import com.intellij.ui.EditorNotifications
import org.zell.idea.lang.ZellFileType
import java.util.function.Function
import javax.swing.JComponent

/** "Download…" and "Settings" actions shared by the editor banner and the balloon. */
object ZellToolchainActions {
    /** The toolchain install instructions (scripts/install.sh, install.ps1, archives). */
    const val INSTALL_URL = "https://github.com/zell-lang/zell#install"

    fun openInstallPage() = BrowserUtil.browse(INSTALL_URL)

    fun openSettings(project: Project?) =
        ShowSettingsUtil.getInstance().showSettingsDialog(project, ZellToolchainConfigurable::class.java)

    /** Adds both actions to a balloon notification. */
    fun addTo(notification: Notification): Notification = notification
        .addAction(NotificationAction.createSimple("Download…") { openInstallPage() })
        .addAction(NotificationAction.createSimpleExpiring("Settings") { openSettings(null) })
}

/**
 * Editor banner on `.zl` files when no `zell` compiler is found (as GoLand
 * does for a missing GOROOT): "Download…" opens the install instructions,
 * "Settings" opens Languages & Frameworks › Zell. Hidden again once the
 * toolchain settings change and a compiler is found.
 */
class ZellToolchainNotificationProvider : EditorNotificationProvider, DumbAware {
    override fun collectNotificationData(project: Project, file: VirtualFile): Function<in FileEditor, out JComponent?>? {
        if (file.fileType != ZellFileType || ZellToolchain.zellExecutable() != null) return null
        return Function { editor ->
            EditorNotificationPanel(editor, EditorNotificationPanel.Status.Warning).apply {
                text = "Zell toolchain not found: run, test, formatting and the language server are unavailable"
                createActionLabel("Download…") { ZellToolchainActions.openInstallPage() }
                createActionLabel("Settings") { ZellToolchainActions.openSettings(project) }
            }
        }
    }

    /** Re-evaluates the banner in every open project when the toolchain settings change. */
    class ToolchainListener : ZellToolchain.Listener {
        override fun toolchainChanged() {
            for (project in com.intellij.openapi.project.ProjectManager.getInstance().openProjects) {
                EditorNotifications.getInstance(project).updateAllNotifications()
            }
        }
    }
}
