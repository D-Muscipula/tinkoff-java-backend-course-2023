package edu.hw5;

import edu.hw5.Task8.Task8;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task8Test {
    @Test
    void functionsContainOnlyOneAndZero() {
        Assertions.assertFalse(Task8.isLengthOdd("0a0"));

        Assertions.assertFalse(Task8.isLengthOddAnd0OrLengthEvenAnd1("010b"));
        Assertions.assertFalse(Task8.isLengthOddAnd0OrLengthEvenAnd1("1b00"));

        Assertions.assertFalse(Task8.isCountOf0DivisibleBy3("b000a"));
        Assertions.assertFalse(Task8.isCountOf0DivisibleBy3("ba"));

        Assertions.assertFalse(Task8.isAnyStringExcept11And111("a"));

        Assertions.assertFalse(Task8.isEveryOddSymbol1("a1a1"));

        Assertions.assertFalse(Task8.isContainingTwoAndMore0AndOneOrZero1("a001"));

        Assertions.assertFalse(Task8.isNotContaining11("a0"));
        Assertions.assertFalse(Task8.isNotContaining11("0a"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "1", "11", "111", "110", "1110", "0111", "11011", "11111", "01010", "1010010101"})
    void isLengthOddTest(String s) {
        Assertions.assertEquals(s.length() % 2 == 1, Task8.isLengthOdd(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "01", "1", "11", "110", "1100", "1110", "0111", "11011", "11111", "00000",
        "101000"})
    void isLengthOddAnd0OrLengthEvenAnd1Test(String s) {
        Assertions.assertEquals(
            !s.isEmpty() && ((s.length() % 2 == 1 && s.charAt(0) == '0'
                || s.length() % 2 == 0 && s.charAt(0) == '1')),
            Task8.isLengthOddAnd0OrLengthEvenAnd1(s)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "00", "000", "10100", "100010", "10000100", "000000000", "1000000000"})
    void isCountOf0DivisibleBy3Test(String s) {
        Assertions.assertEquals(
            s.chars().filter(x -> x == '0').count() % 3 == 0,
            Task8.isCountOf0DivisibleBy3(s)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "11", "111", "110", "1110", "011", "0111", "111011", "1111", "01010",
        "1010010101"})
    void isAnyStringExcept11And111Test(String s) {
        //System.out.println(s + Task8.isAnyStringExcept11And111(s));
        Assertions.assertEquals(!(s.equals("11") || s.equals("111")), Task8.isAnyStringExcept11And111(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "11", "111", "1111", "0", "010", "0101", "111011", "01010101", "01000",
        "01010100"})
    void isEveryOddSymbol1Test(String s) {
        boolean isOdd1 = true;
        for (int i = 1; i < s.length(); i += 2) {
            if (s.charAt(i) == '0') {
                isOdd1 = false;
                break;
            }
        }
        Assertions.assertEquals(isOdd1, Task8.isEveryOddSymbol1(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "0", "00", "001", "100", "010", "0101", "000100", "0100001", "01000", "1001",
        "00000", "00000"})
    void isContainingTwoAndMore0AndOneOrZero1Test(String s) {
        Assertions.assertEquals(s.chars().filter(x -> x == '0').count() >= 2
            && s.chars().filter(x -> x == '1').count() <= 1, Task8.isContainingTwoAndMore0AndOneOrZero1(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "0", "10", "11", "011", "110", "101", "111", "0101", "1111", "01010", "0000000001100000",
        "1010010101"})
    void isNotContaining11Test(String s) {
        Assertions.assertEquals(!s.contains("11"), Task8.isNotContaining11(s));
    }
}
