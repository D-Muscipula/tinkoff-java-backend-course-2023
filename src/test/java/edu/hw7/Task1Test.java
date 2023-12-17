package edu.hw7;

import edu.hw7.Task1.Task1;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void changeNumTest() throws InterruptedException {
        Assertions.assertInstanceOf(AtomicInteger.class, Task1.changeNum(1, 15));
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(10000, Task1.changeNum(0, 10000).get());
        }
    }
}
