package edu.hw8;

import edu.hw8.Task2.NewFixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;

public class Task2Test {
    private static int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    @Test
    void testNewFixedThreadPool() throws InterruptedException {
        CopyOnWriteArrayList<Integer> expected = new CopyOnWriteArrayList<>() {{
            add(0);
            add(1);
            add(1);
            add(2);
            add(3);
            add(5);
            add(8);
            add(13);
            add(21);
            add(34);
        }};
        CopyOnWriteArrayList<Integer> actual = new CopyOnWriteArrayList<>();
        CountDownLatch latch = new CountDownLatch(10);
        try (ThreadPool threadPool = NewFixedThreadPool.create(4)) {
            threadPool.start();

            for (int i = 0; i < 10; i++) {
                final int index = i;
                threadPool.execute(() -> {
                    int fib = calculateFibonacci(index);
                    actual.add(fib);
                    System.out.println("Fibonacci(" + index + ") = " + fib);
                    latch.countDown();
                });
            }
            latch.await();
            //threadPool.close();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(actual);
        Collections.sort(actual);
        Assertions.assertEquals(expected, actual);
    }
}
