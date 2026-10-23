package org.zell.idea.run

/** The `zell` subcommand a run configuration executes. */
enum class ZellCommand(val cli: String, val title: String) {
    RUN("run", "Run"),
    BUILD("build", "Build"),
    TEST("test", "Test"),
}
