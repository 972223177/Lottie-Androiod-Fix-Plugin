package com.ly.lottiefix.core

import org.objectweb.asm.MethodVisitor

/**
 * 解决Lottie在Cancel后也调用了onAnimationEnd的问题
 */
class LAVMethodVisitor(api:Int,mv: MethodVisitor) : MethodVisitor(api, mv) {

    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        if (owner == LOTTIE_VALUE_ANIMATOR) {
            if (!(name == IS_REVERSED && descriptor == IS_REVERSED_DESC) && !(name == NOTIFY_END && descriptor == NOTIFY_END_DESC)) {
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
            }
            //如果notifyEnd和isReversed的话就什么都不做
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }
    }

    companion object {
        private const val LOTTIE_VALUE_ANIMATOR = "com/airbnb/lottie/utils/LottieValueAnimator"
        private const val IS_REVERSED = "isReversed"
        private const val IS_REVERSED_DESC = "()Z"
        private const val NOTIFY_END = "notifyEnd"
        private const val NOTIFY_END_DESC = "(Z)V"
    }
}