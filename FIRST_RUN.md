# First real build (record)

The plugin was scaffolded without access to the JetBrains repositories; the
first build against the IntelliJ Platform ran on 2026-10-23 (Fedora 42,
Gradle 8.10, IU-242.20224.300, zell `91c4ea1`). This file records what it
found, so the next build on a new machine knows what to expect.

## Environment notes

| Problem | Fix |
|---|---|
| Only a JRE 21 installed (no `javac`): `Cannot find a Java installation ... languageVersion=21` | `settings.gradle.kts` applies `org.gradle.toolchains.foojay-resolver-convention` 0.8.0; Gradle downloads a JDK 21 into `~/.gradle/jdks` |
| `download.jetbrains.com` answers 451 from some networks | Gradle does not read `HTTP(S)_PROXY`; pass the proxy as JVM properties: `./gradlew -Dhttps.proxyHost=... -Dhttps.proxyPort=... [-Dhttps.proxyUser=... -Dhttps.proxyPassword=...] -Djdk.http.auth.tunneling.disabledSchemes= ...` (same for `http.`) |
| Platform tests: `NoClassDefFoundError: org/opentest4j/AssertionFailedError` | `testImplementation("org.opentest4j:opentest4j:1.3.0")` |

## Results

| Step | Result |
|---|---|
| `tools/sync-zell.sh` (`ZELL_REPO=<local clone>`) | no grammar patches; `Zell.bnf` differs from the previous pin only in the header comment |
| `generateZellLexer generateZellParser` | generated as expected (`ZellParserUtil` import, stub factories, `Zell*DeclImpl` with the stub constructors); `src/main/gen` is now committed |
| `compileKotlin compileJava` | compiled at the first attempt, including the LSP code written from documentation |
| `test` | lexer 30/30; corpus: **1160 files parsed, 1 skipped (lexer error), 0 parse errors** (`examples/**`, `tests/spec/**`, `std/**`); highlighting, folding, structure; run, console filter, test events, templates; toolchain integration tests with `ZELL_TOOLCHAIN` |

## `TODO(verify)` resolutions

Checked by compiling and with `javap` on `lib/product.jar` of IU-242 (the
platform LSP API lives there; no `bundledModule(...)` needed).

| Where | Result |
|---|---|
| `build.gradle.kts` (dependencies) | LSP API on the compile classpath as is |
| `build.gradle.kts` (`pluginVerification`) | see the verifier result in README / the last commit |
| `stubs/ZellDeclStubElementType.kt` | generated `ZellStructDeclImpl` ... `ZellConstDeclImpl` with `(ZellDeclStub, IStubElementType)` constructors |
| `LspServerSupportProvider.fileOpened(project, file, serverStarter)`, `ensureServerStarted`, `ProjectWideLspServerDescriptor(project, name)`, `isSupportedFile`, `createCommandLine` | as written |
| `lspGoToDefinitionSupport`, `lspHoverSupport` (Boolean), `lspFormattingSupport` (`shouldFormatThisFileExclusivelyByServer(file, Boolean, Boolean)`) | `open val`s in 242 |
| `getLanguageId(file)` | overridable |
| `createInitializationOptions(): Any?` | exists; zell-lsp answers `initialize` with these options without a pull `diagnosticProvider` (`ZellToolchainIntegrationTest.testLspInitialize`) |
| `lspCommandsSupport` / `LspCommandsSupport.executeCommand(server, file, command)` | exists. 242 has no LSP code lens, semantic token or inlay hint support (customizations present: completion, diagnostics, code actions, commands, formatting, find references), so the code-lens commands are only reached on newer builds; the gutter run markers cover main and tests |
| `RunContentExecutor(project, handler).withTitle/withActivateToolWindow/run` | as written |
| `LspServerManager.stopAndRestartIfNeeded(Class)` | exists |

## Still to check by hand (`./gradlew runIde`, needs a desktop)

Open `build/zell` as a project, then `examples/hello.zl`: colours, folding,
Structure, Navigate › Symbol, **Zell std** under External Libraries; with
`zell-lsp`: diagnostics, hover, Ctrl+B, completion, Reformat Code, Tools ›
Restart Zell Language Server. Run gutter on `func main` and on `@test`
functions; the test tree of a `*_test.zl` file; clickable `file.zl:LINE` in
an error trace; live templates (`test`, `iferr`); File › New › Zell File;
File › New › Project › Zell.
