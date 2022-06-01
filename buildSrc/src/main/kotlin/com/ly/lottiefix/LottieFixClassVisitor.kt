package com.ly.lottiefix

import org.objectweb.asm.*


class LottieFixClassVisitor(cv: ClassVisitor) : ClassVisitor(Opcodes.ASM7, cv) {
    private var mClassName = ""
    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        mClassName = name ?: ""
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor? {
        val mv = cv.visitMethod(access, name, descriptor, signature, exceptions)
        return when (mClassName) {
            IMAGE_ASSET_MANAGER -> {
                if (name == BITMAP_FOR_ID && descriptor == BITMAP_FOR_ID_METHOD_DESC) {
                    Log.info("fix ImageAssetManager bitmapForId")
                    IAMMethodVisitor(mv)
                } else {
                    mv
                }
            }
            LOTTIE_VALUE_ANIMATOR -> {
                if (name == NOTIFY_CANCEL && descriptor == NOTIFY_CANCEL_METHOD_DESC) {
                    Log.info("fix LottieValueAnimator notifyCancel")
                    LAVMethodVisitor(mv)
                } else {
                    mv
                }
            }
            else -> {
                mv
            }
        }
    }


    companion object {
        private const val BITMAP_FOR_ID = "bitmapForId"
        private const val BITMAP_FOR_ID_METHOD_DESC =
            "(Ljava/lang/String;)Landroid/graphics/Bitmap;"

        private const val NOTIFY_CANCEL = "notifyCancel"
        private const val NOTIFY_CANCEL_METHOD_DESC = "()V"

        private const val IMAGE_ASSET_MANAGER = "com/airbnb/lottie/manager/ImageAssetManager"
        private const val LOTTIE_VALUE_ANIMATOR = "com/airbnb/lottie/utils/LottieValueAnimator"

    }
}