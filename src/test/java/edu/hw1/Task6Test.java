package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    void countKCommonSituations() {
        Assertions.assertEquals(Task6.countK(3524), 3);
        Assertions.assertEquals(Task6.countK(6621), 5);
        Assertions.assertEquals(Task6.countK(6554), 4);
        Assertions.assertEquals(Task6.countK(1234), 3);
    }

    @Test
    @DisplayName("Может так получится, что у некоторых чисел разность" +
        "получившихся чисел меньше 1000, в таких случаях дописывается 0")
    void countKResultIsLessThanThousand() {
        Assertions.assertEquals(Task6.countK(2221), 5);//2221 - 1222 = 999
        Assertions.assertEquals(Task6.countK(9998), 5);//9998 - 8999 = 999
    }

    @Test
    void countKIncorrectNumberOfDigits() {
        Assertions.assertEquals(Task6.countK(1), -1);
        Assertions.assertEquals(Task6.countK(998), -1);
        Assertions.assertEquals(Task6.countK(12345), -1);
    }

    @Test
    void countKTheSameDigits() {
        Assertions.assertEquals(Task6.countK(1111), -1);
        Assertions.assertEquals(Task6.countK(3333), -1);
        Assertions.assertEquals(Task6.countK(9999), -1);
    }
}
