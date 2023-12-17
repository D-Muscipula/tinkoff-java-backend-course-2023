package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    public void changeMethod() {
        ByteBuddyAgent.install();
        try{
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(NewArithmeticUtils.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();
        Assertions.assertEquals(6,ArithmeticUtils.sum(2, 3));
        } catch (Exception ignored) {

        }


    }

    private static final class NewArithmeticUtils {

        public static int multiple(int a, int b) {
            return a * b;
        }
    }
}
