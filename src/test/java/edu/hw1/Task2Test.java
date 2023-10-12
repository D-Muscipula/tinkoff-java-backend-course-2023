package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void countDigitsCommonSituations() {
        Assertions.assertEquals(Task2.countDigits(4666), 4);
        Assertions.assertEquals(Task2.countDigits(544), 3);
        Assertions.assertEquals(Task2.countDigits(123123123), 9);
    }

    @Test
    void countDigitsZeroAndNegative() {
        Assertions.assertEquals(Task2.countDigits(0), 1);
        Assertions.assertEquals(Task2.countDigits(-1), 1);
        Assertions.assertEquals(Task2.countDigits(-12312), 5);
    }
}
