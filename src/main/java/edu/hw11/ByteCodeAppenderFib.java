package edu.hw11;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public class ByteCodeAppenderFib implements ByteCodeAppender {

    private static final String CLASS_NAME = "Fibonacci";
    private static final String FUNCTION_NAME = "getFib";
    private static final String FUNCTION_SIGNATURE = "(I)J";
    private static final int STACK_SIZE = 5;

    @Override
    public @NotNull Size apply(
        MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription
    ) {
        Label condition = new Label();

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, condition);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitLabel(condition);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.ISUB);

        methodVisitor.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            CLASS_NAME,
            FUNCTION_NAME,
            FUNCTION_SIGNATURE,
            false
        );

        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitInsn(Opcodes.ISUB);

        methodVisitor.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            CLASS_NAME,
            FUNCTION_NAME,
            FUNCTION_SIGNATURE,
            false
        );
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        return new ByteCodeAppender.Size(STACK_SIZE, 0);
    }
}
