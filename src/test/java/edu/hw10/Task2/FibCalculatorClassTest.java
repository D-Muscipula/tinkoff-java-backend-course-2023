package edu.hw10.Task2;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FibCalculatorClassTest {
    @Test
    void withCacheProxyTest() {
        FibCalculator fibCalculator = new FibCalculatorClass();
        FibCalculator proxyFibCalculator =
            CacheProxy.create(fibCalculator, FibCalculator.class, "src\\test\\java\\edu\\hw10\\Task2\\dirForTests\\test");
        Assertions.assertEquals(proxyFibCalculator.getFib(10), 55);
        File file = new File("src\\test\\java\\edu\\hw10\\Task2\\dirForTests\\test");
        file.delete();
    }


    @Test
    void withHandlerTest() {
        FibCalculator fibCalculator = new FibCalculatorClass();
        FibCalculator proxyFibCalculator =
            CacheProxy.create(fibCalculator, FibCalculator.class, "src\\test\\java\\edu\\hw10\\Task2\\dirForTests\\test");
        Assertions.assertEquals(55, proxyFibCalculator.getFib(10));

        var handler = new CacheInvocationHandler(fibCalculator, "src\\test\\java\\edu\\hw10\\Task2\\dirForTests\\test");
        long a = (long) handler.getCache().get("Class: edu.hw10.Task2.FibCalculatorMethod: getFibArgs: 10");
        Assertions.assertEquals(55, a);

        Assertions.assertEquals(610, proxyFibCalculator.getFibPersistFalse(15));
        Assertions.assertThrows(NullPointerException.class, () -> {
            handler.getCache().get("Class: edu.hw10.Task2.FibCalculatorMethod: getFibArgs: 15");
        });

        File file = new File("src\\test\\java\\edu\\hw10\\Task2\\dirForTests\\test");
        file.delete();
    }
}
