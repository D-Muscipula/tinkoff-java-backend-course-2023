package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task3Test {
    @Test
    void testFibonacci() {
        Class<?> newClass = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("getFib", long.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(
                new ByteCodeAppenderFib()))
            .make()
            .load(ByteCodeAppenderFib.class.getClassLoader())
            .getLoaded();
        try {
            Object instance = newClass.getConstructor().newInstance();
            Method fibMethod = newClass.getMethod("getFib", int.class);
            Assertions.assertEquals(89L, fibMethod.invoke(instance, 11));
            Assertions.assertEquals(144L, fibMethod.invoke(instance, 12));
            Assertions.assertEquals(610L, fibMethod.invoke(instance, 15));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }
}
