package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    void isPalindromeDescendantCommonSituations() {
        assert (Task5.isPalindromeDescendant(11211230));
        assert (Task5.isPalindromeDescendant(13001120));
        assert (Task5.isPalindromeDescendant(23336014));
    }

    @Test
    void isPalindromeDescendantSmallNumbers() {
        assert (Task5.isPalindromeDescendant(11));
        assert (Task5.isPalindromeDescendant(3));
        assert (!Task5.isPalindromeDescendant(-11));
        assert (!Task5.isPalindromeDescendant(17));
        assert (!Task5.isPalindromeDescendant(-1861));
    }
    @Test
    @DisplayName("Если у числа появляется потомок с нечетным количеством цифр," +
        "который не является палиндромом, то false")
    void isPalindromeDescendantOddQuantityOfDigitsOfNumberOrDescendant() {
        assert (Task5.isPalindromeDescendant(121));
        assert (Task5.isPalindromeDescendant(121112));
        assert (!Task5.isPalindromeDescendant(121113));
    }
}
