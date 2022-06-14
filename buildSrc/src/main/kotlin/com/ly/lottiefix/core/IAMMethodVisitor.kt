package com.ly.lottiefix.core

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*

/**
 * 解决lottie播放过程中，突然加载不存在的文件导致崩溃的情况
 */
class IAMMethodVisitor(api:Int,mv: MethodVisitor) : MethodVisitor(api, mv) {


    override fun visitInsn(opcode: Int) {
        if (opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
            mv.visitLdcInsn("LottieFixPlugin ------ unknown image source") //打印下日志
            mv.visitMethodInsn(
                INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false
            )
            mv.visitInsn(ACONST_NULL)
            mv.visitInsn(ARETURN)// 直接返回空值
        } else {
            super.visitInsn(opcode)
        }
    }

}