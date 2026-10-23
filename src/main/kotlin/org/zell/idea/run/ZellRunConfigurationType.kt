package org.zell.idea.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.ConfigurationTypeUtil
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project
import org.zell.idea.lang.ZellIcons

/** Run | Edit Configurations | Zell: `zell run`, `zell build` and `zell test`. */
class ZellRunConfigurationType : ConfigurationTypeBase(
    ID,
    "Zell",
    "Run, build or test a Zell file or package with the zell compiler",
    ZellIcons.FILE,
) {
    init {
        addFactory(Factory(this))
    }

    val factory: ConfigurationFactory
        get() = configurationFactories.single()

    class Factory(type: ZellRunConfigurationType) : ConfigurationFactory(type) {
        override fun getId(): String = ID

        override fun createTemplateConfiguration(project: Project): RunConfiguration =
            ZellRunConfiguration(project, this, "Zell")

        override fun getOptionsClass(): Class<out BaseState> = ZellRunConfigurationOptions::class.java
    }

    companion object {
        const val ID = "ZellRunConfiguration"

        fun getInstance(): ZellRunConfigurationType =
            ConfigurationTypeUtil.findConfigurationType(ZellRunConfigurationType::class.java)
    }
}
