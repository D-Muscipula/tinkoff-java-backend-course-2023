package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    void isPalindromeDescendantCommonSituations() {
        Assertions.assertTrue(Task5.isPalindromeDescendant(11211230));
        Assertions.assertTrue(Task5.isPalindromeDescendant(13001120));
        Assertions.assertTrue(Task5.isPalindromeDescendant(23336014));
    }

    @Test
    void isPalindromeDescendantSmallNumbers() {
        Assertions.assertTrue(Task5.isPalindromeDescendant(11));
        Assertions.assertTrue(Task5.isPalindromeDescendant(3));
        Assertions.assertFalse(Task5.isPalindromeDescendant(-11));
        Assertions.assertFalse(Task5.isPalindromeDescendant(17));
        Assertions.assertFalse(Task5.isPalindromeDescendant(-1861));
    }

    @Test
    @DisplayName("Если у числа появляется потомок с нечетным количеством цифр," +
        "который не является палиндромом, то false")
    void isPalindromeDescendantOddQuantityOfDigitsOfNumberOrDescendant() {
        Assertions.assertTrue(Task5.isPalindromeDescendant(121));
        Assertions.assertTrue(Task5.isPalindromeDescendant(121112));
        Assertions.assertFalse(Task5.isPalindromeDescendant(121113));
    }
}
