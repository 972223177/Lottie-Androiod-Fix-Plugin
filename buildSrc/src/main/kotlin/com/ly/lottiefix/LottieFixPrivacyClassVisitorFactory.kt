package com.ly.lottiefix

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import com.ly.lottiefix.core.LottieFixClassVisitor
import org.objectweb.asm.ClassVisitor

abstract class LottieFixPrivacyClassVisitorFactory :
    AsmClassVisitorFactory<InstrumentationParameters.None> {

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        return LottieFixClassVisitor(nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData): Boolean =
        classData.className == LottieFixClassVisitor.LOTTIE_VALUE_ANIMATOR || classData.className == LottieFixClassVisitor.IMAGE_ASSET_MANAGER
}