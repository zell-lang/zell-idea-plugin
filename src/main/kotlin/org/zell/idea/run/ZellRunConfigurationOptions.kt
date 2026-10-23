package org.zell.idea.run

import com.intellij.execution.configurations.LocatableRunConfigurationOptions

/** Persistent fields of [ZellRunConfiguration]. */
class ZellRunConfigurationOptions : LocatableRunConfigurationOptions() {
    /** [ZellCommand] name. */
    var command by string(ZellCommand.RUN.name)

    /** File, package directory or empty (the package of the working directory). */
    var target by string("")

    /** Options for the compiler, before the target (`--release`, `--target ...`). */
    var compilerOptions by string("")

    /** Arguments of the program (`zell run ... -- args`); ignored by build and test. */
    var programArguments by string("")

    /** `zell test --filter` regular expression. */
    var testFilter by string("")

    var workingDirectory by string("")

    var env by map<String, String>()

    var passParentEnv by property(true)
}
