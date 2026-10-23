# Zell for IntelliJ

IntelliJ Platform plugin for the [Zell](https://github.com/zell-lang/zell)
language. Syntax comes from a Grammar-Kit PSI generated from the language's
canonical grammar (`grammar/zell.bnf`); semantics come from the Zell
language server `zell-lsp`, hosted through the platform's own LSP API
(`com.intellij.platform.lsp`), the way the Go plugin hosts `gopls`.

The project was formerly named Slang (`.sl` files, `slang`/`slang-lsp`, plugin id `org.slang.idea`).

Status: milestones 1 (skeleton) and 2 (LSP hosting) done, milestone 3 (run
and test) done, the templates and the wizard of
milestone 5 done; the debugger (milestone 4) is designed, not written.
Built, tested and verified against IntelliJ IDEA Ultimate 2024.2; see
[FIRST_RUN.md](FIRST_RUN.md) for what the first build found.

## Install

Every CI build on `main` is published as a pre-release on the
[releases page](https://github.com/zell-lang/zell-idea-plugin/releases):
`https://github.com/zell-lang/zell-idea-plugin/releases/tag/v<version>`, with the zip at
`https://github.com/zell-lang/zell-idea-plugin/releases/download/v<version>/zell-idea-plugin-<version>.zip`.
Download the zip, then in the IDE: **Settings | Plugins | ⚙ | Install
Plugin from Disk…** and pick it (do not unpack it). A local build
(`./gradlew buildPlugin`) puts the zip in `build/distributions/`.

Versions are `YYYY.M.N`: year and month of the build plus the CI run number,
so every build differs (`.github/workflows/build.yml` passes
`-PpluginVersion=$(date -u +%Y.%-m).<run_number>`; `PLUGIN_VERSION` in the
environment works too). Local builds use `pluginVersion` from
`gradle.properties` (`2026.10.0-local`). The change notes in the plugin and
the release notes come from the **Unreleased** section of
[CHANGELOG.md](CHANGELOG.md); the release notes add the commits since the
previous release tag.

Supported IDEs: 2024.2 – 2025.2 (builds 242 – 252.*). The LSP features need
a commercial IDE (IntelliJ IDEA Ultimate, GoLand, WebStorm, …); in IntelliJ
IDEA Community the plugin loads with the PSI features only.

Install the toolchain with `scripts/install.sh` (or `install.ps1`) from the
zell repository; it links `zell` and `zell-lsp` into `~/.local/bin`.

## Features

| Milestone | Feature | Source |
|---|---|---|
| 1 | `.zl` file type and icon; Zell manifest icon on `zell.toml` / `zell.json` / `zell.yaml` | plugin |
| 1 | Highlighting: keywords, literals, comments, operators, `?` | lexer (JFlex) |
| 1 | Declaration names, attributes (`@json`), namespace paths | PSI annotator |
| 1 | Brace matching, `//` and `/* */` commenting | plugin |
| 1 | Folding: bodies, multi-line comments, import groups | PSI |
| 1 | Auto-closing `"…"`, `'…'` and `` `raw` `` strings; indentation on Enter inside `{}`, `()`, `[]` | plugin |
| 1 | TODO / FIXME in comments (TODO tool window), spellchecking of comments, strings and declaration names | plugin |
| 1 | Breadcrumbs: enclosing struct / interface / enum / function | PSI |
| 1 | Code › Reformat Code and Actions on Save › Reformat code: `zell fmt --stdin` (all IDEs) | `zell` CLI |
| 1 | Structure view: namespace › types › fields/methods, functions, constants | PSI |
| 1 | Navigate › Symbol over top-level declarations | stub index |
| 1 | Color scheme page (Settings › Editor › Color Scheme › Zell) | plugin |
| 2 | Standard library (`zell env ZELL_STD`) as an external library | toolchain |
| 2 | Diagnostics, quick fixes, code actions, go to definition, references, hover, completion, formatting (`zell fmt`) | zell-lsp |
| 2 | Semantic tokens, inlay hints, code lenses (offered by zell-lsp; rendered only by platform builds newer than 242) | zell-lsp |
| 2 | Tools › Restart Zell Language Server; zell-lsp in the status bar's Language Services widget (state, restart, stop, settings) | plugin + platform |
| 2 | Quick documentation (Ctrl+Q) and hover from zell-lsp | platform LSP client |
| 2 | Editor banner when no toolchain is found (Download…, Settings); detected `zell` / `zell-lsp` versions on the settings page | plugin |
| 2 | Code lens commands: Run test, Debug test (runs with `-v`), Run, N references (`zell.runTest`, `zell.debugTest`, `zell.run`, `zell.showReferences`) | zell-lsp + plugin |
| 3 | Run configuration **Zell**: `zell run`, `zell build`, `zell test` (target, compiler options, program arguments, test filter, working directory, environment) | plugin |
| 3 | Gutter Run markers on `func main` and `@test` functions; Run from the editor/project view | configuration producers |
| 3 | Test tree from `zell test --json`, with navigation to the test function | SMTRunner |
| 3 | Links for `file.zl:LINE[:COL]` in any console: diagnostics, error traces, test reports | console filter |
| 4 | Debugger over the in-process agent (DAP) | planned, see CLAUDE.md |
| 5 | Live templates: `func`, `afunc`, `main`, `struct`, `test`, `for`, `fori`, `iferr` (`try … catch err`), `if` | plugin |
| 5 | Surround With › `try { … } catch err { … }`; postfix templates `.var`, `.return` | plugin |
| 5 | File › New › Zell File: plain file, main program, test file (`<name>_test.zl`); namespace from a sibling file, the manifest or the folder | file templates |
| 5 | File › New › Project › Zell (`zell init`, optional `--lib` and manifest format) | wizard |

## Settings

**Settings | Languages & Frameworks | Zell**

- `zell` — the compiler. Empty: found on `PATH`, then in
  `$ZELL_HOME/toolchain/current/bin` and `~/.zell/toolchain/current/bin`.
- `zell-lsp` — the language server. Empty: found on `PATH` or next to
  `zell`; when only `zell` is found the IDE runs `zell lsp`.
- `ZELL_HOME` — used for `std/` when `zell env ZELL_STD` is unavailable.

Changing the settings restarts the language server and re-attaches the
standard library.

## Development

```sh
tools/sync-zell.sh                 # build/zell at the pinned commit + src/main/grammar/Zell.bnf
./gradlew test                      # lexer, parser corpus, highlighting, folding, structure, run, template tests
ZELL_TOOLCHAIN=../zell/target/release ./gradlew test   # also zell init / zell-lsp integration tests
./gradlew runIde                    # sandbox IDE (IntelliJ IDEA Ultimate 2024.2)
./gradlew buildPlugin verifyPlugin  # zip + Plugin Verifier (IC 2024.2.4 and IU 2025.2; slow, CI runs it)
```

The grammar is synced from the zell repository at `zellCommit`
(`gradle.properties`); pending grammar fixes that are meant for upstream
live in `tools/grammar-patches/`. See [CLAUDE.md](CLAUDE.md) for the
architecture and rules.
