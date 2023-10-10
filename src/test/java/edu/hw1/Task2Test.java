package edu.hw1;

import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void countDigitsCommonSituations() {
        assert (Task2.countDigits(4666) == 4);
        assert (Task2.countDigits(544) == 3);
        assert (Task2.countDigits(123123123) == 9);
    }
    @Test
    void countDigitsZeroAndNegative() {
        assert (Task2.countDigits(0) == 1);
        assert (Task2.countDigits(-1) == 1);
        assert (Task2.countDigits(-12312) == 5);
    }
}
