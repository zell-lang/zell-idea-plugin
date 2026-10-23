# Changelog

Versions are `YYYY.M.N`: the year and month of the build and the CI run
number, so every build has its own version. Each successful CI build on
`main` is published as a pre-release on the
[releases page](https://github.com/zell-lang/zell-idea-plugin/releases).
The release notes and the plugin's change notes are taken from the
**Unreleased** section below, followed by the commits since the previous
release. Move entries under a dated heading when cutting a stable release.

## Unreleased

### Added
- Date-based plugin versions (`YYYY.M.N`) and a GitHub pre-release with the installable zip for every CI build on `main`

## 2026.10.0

### Added
- Syntax highlighting, brace matching, commenting, folding, structure view and Go to Symbol for `.zl` files
- `zell-lsp` hosting through the platform LSP API: diagnostics, navigation, hover, completion, rename, code actions, formatting
- Run configurations for `zell run`, `zell build` and `zell test` with a test tree and console links
- Live templates, File › New › Zell File and the New Project wizard
