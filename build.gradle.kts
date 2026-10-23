import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    // The version comes from the settings plugin in settings.gradle.kts (2.9.0,
    // the newest 2.x that still runs on Gradle 8.10; 2.10+ needs Gradle 8.13).
    id("org.jetbrains.intellij.platform")
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = providers.gradleProperty("pluginGroup").get()

// Plugin version: `YYYY.M.N`. CI passes `-PpluginVersion=$(date -u +%Y.%-m).<run_number>`
// (or sets PLUGIN_VERSION) so every build has its own version; a local build
// uses `pluginVersion` from gradle.properties (a `-local` version).
val pluginVersion: Provider<String> = providers.environmentVariable("PLUGIN_VERSION")
    .orElse(providers.gradleProperty("pluginVersion"))
version = pluginVersion.get()

/**
 * The "Unreleased" section of CHANGELOG.md as HTML for `<change-notes>`:
 * `### Heading` becomes `<h3>`, `- item` becomes `<li>`, `code` spans become `<code>`.
 */
fun unreleasedChangeNotes(changelog: File): String {
    if (!changelog.isFile) return ""
    val section = changelog.readLines()
        .dropWhile { !it.trim().equals("## Unreleased", ignoreCase = true) }
        .drop(1)
        .takeWhile { !it.startsWith("## ") }
    fun inline(text: String) = text
        .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
        .replace(Regex("`([^`]+)`"), "<code>$1</code>")
    val html = StringBuilder()
    var inList = false
    for (line in section.map { it.trim() }.filter { it.isNotEmpty() }) {
        val item = line.startsWith("- ") || line.startsWith("* ")
        if (item && !inList) html.append("<ul>")
        if (!item && inList) html.append("</ul>")
        inList = item
        when {
            item -> html.append("<li>").append(inline(line.substring(2))).append("</li>")
            line.startsWith("#") -> html.append("<h3>").append(inline(line.trimStart('#').trim())).append("</h3>")
            else -> html.append("<p>").append(inline(line)).append("</p>")
        }
    }
    if (inList) html.append("</ul>")
    return html.toString()
}

// IntelliJ Platform 2024.2 (242) runs on and is compiled for Java 21.
kotlin {
    jvmToolchain(21)
    compilerOptions {
        // Do not use stdlib API newer than the Kotlin bundled with 2024.2.
        apiVersion = KotlinVersion.KOTLIN_1_9
    }
}

sourceSets {
    main {
        // Grammar-Kit / JFlex output (committed, see CLAUDE.md).
        java.srcDir("src/main/gen")
    }
}

dependencies {
    intellijPlatform {
        // The platform LSP API (com.intellij.platform.lsp.api) is only in commercial IDEs;
        // in 242 it is part of lib/product.jar, on the classpath without bundledModule().
        intellijIdeaUltimate(providers.gradleProperty("platformVersion"))
        testFramework(TestFrameworkType.Platform)
        pluginVerifier()
        zipSigner()
    }
    testImplementation("junit:junit:4.13.2")
    // The platform test framework references opentest4j (assertion classes) without shipping it.
    testImplementation("org.opentest4j:opentest4j:1.3.0")
}

intellijPlatform {
    // No forms and no @NotNull instrumentation needed; avoids the extra
    // instrumentation-tools download.
    instrumentCode = false
    buildSearchableOptions = false

    pluginConfiguration {
        id = "org.zell.idea"
        name = providers.gradleProperty("pluginName")
        version = pluginVersion
        changeNotes = providers.provider { unreleasedChangeNotes(layout.projectDirectory.file("CHANGELOG.md").asFile) }
        ideaVersion {
            sinceBuild = providers.gradleProperty("pluginSinceBuild")
            untilBuild = providers.gradleProperty("pluginUntilBuild")
        }
    }

    pluginVerification {
        ides {
            // Two IDEs keep CI cheap: the oldest supported Community build (no
            // platform LSP API, so the optional zell-lsp.xml must stay unloaded) and
            // the newest supported Ultimate (LSP API as of untilBuild). Ultimate
            // 2024.2 is the compile target and needs no extra run.
            create(IntelliJPlatformType.IntellijIdeaCommunity, "2024.2.4")
            create(IntelliJPlatformType.IntellijIdeaUltimate, "2025.2")
        }
    }
}

// ---------------------------------------------------------------------------
// Grammar-Kit: lexer from src/main/grammar/Zell.flex, parser + PSI from
// src/main/grammar/Zell.bnf (copied from the zell repo by tools/sync-zell.sh).
// ---------------------------------------------------------------------------

val generateZellLexer by tasks.registering(GenerateLexerTask::class) {
    sourceFile = layout.projectDirectory.file("src/main/grammar/Zell.flex")
    // purgeOldFiles wipes this whole directory: keep it lexer-only.
    targetOutputDir = layout.projectDirectory.dir("src/main/gen/org/zell/idea/lexer")
    purgeOldFiles = true
}

val generateZellParser by tasks.registering(GenerateParserTask::class) {
    sourceFile = layout.projectDirectory.file("src/main/grammar/Zell.bnf")
    targetRootOutputDir = layout.projectDirectory.dir("src/main/gen")
    pathToParser = "org/zell/idea/parser/ZellParser.java"
    pathToPsiRoot = "org/zell/idea/psi"
    purgeOldFiles = true
}

tasks {
    compileJava { dependsOn(generateZellLexer, generateZellParser) }
    compileKotlin { dependsOn(generateZellLexer, generateZellParser) }

    test {
        // The parser corpus test reads build/zell (tools/sync-zell.sh).
        systemProperty("zell.corpus", layout.buildDirectory.dir("zell").get().asFile.absolutePath)
        inputs.dir(layout.buildDirectory.dir("zell")).optional().withPropertyName("zellCorpus")
        // Directory with `zell` and `zell-lsp` for the toolchain integration tests
        // (skipped when unset), e.g. ZELL_TOOLCHAIN=../zell/target/release.
        providers.environmentVariable("ZELL_TOOLCHAIN").orNull?.let { systemProperty("zell.toolchain", it) }
    }
}
