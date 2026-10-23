package org.zell.idea

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.zell.idea.lang.ZellIcons
import org.zell.idea.lang.ZellManifestIconProvider
import org.zell.idea.toolchain.ZellToolchainConfigurable

/** Manifest icons and the toolchain version line of the settings page. */
class ZellIconsTest : BasePlatformTestCase() {
    fun testManifestIcon() {
        val provider = ZellManifestIconProvider()
        for (name in listOf("zell.toml", "zell.json", "zell.yaml")) {
            val file = myFixture.addFileToProject("pkg/$name", "").virtualFile
            assertSame(name, ZellIcons.MANIFEST, provider.getIcon(file, 0, project))
        }
        val other = myFixture.addFileToProject("pkg/Cargo.toml", "").virtualFile
        assertNull(provider.getIcon(other, 0, project))
    }

    fun testDescribeMissingExecutable() {
        assertEquals("not found", ZellToolchainConfigurable.describe(null))
    }
}
