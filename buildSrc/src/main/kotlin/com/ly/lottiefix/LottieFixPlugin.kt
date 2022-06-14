package com.ly.lottiefix

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class LottieFixPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val androidComponentsExtension =
            target.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponentsExtension.onVariants { variant ->
            variant.instrumentation.transformClassesWith(
                LottieFixPrivacyClassVisitorFactory::class.java, InstrumentationScope.ALL
            ) {}
            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
        }
    }
}