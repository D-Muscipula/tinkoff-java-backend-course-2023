package edu.hw3;

import edu.hw3.Task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task4Test {
    static Arguments[] digits() {
        return new Arguments[] {
            Arguments.of(1000, "M"),
            Arguments.of(900, "CM"),
            Arguments.of(500, "D"),
            Arguments.of(400, "CD"),
            Arguments.of(100, "C"),
            Arguments.of(90, "XC"),
            Arguments.of(50, "L"),
            Arguments.of(40, "XL"),
            Arguments.of(10, "X"),
            Arguments.of(9, "IX"),
            Arguments.of(5, "V"),
            Arguments.of(4, "IV"),
            Arguments.of(1, "I"),
        };
    }

    @Test
    void commonSituationsTest() {
        Assertions.assertEquals("II", Task4.convertToRoman(2));
        Assertions.assertEquals("XII", Task4.convertToRoman(12));
        Assertions.assertEquals("XVI", Task4.convertToRoman(16));
        Assertions.assertEquals("XXX", Task4.convertToRoman(30));
        Assertions.assertEquals("XCIV", Task4.convertToRoman(94));
        Assertions.assertEquals("XCIX", Task4.convertToRoman(99));
        Assertions.assertEquals("CMXCIX", Task4.convertToRoman(999));
        Assertions.assertEquals("MCMXCIX", Task4.convertToRoman(1999));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task4.convertToRoman(0));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task4.convertToRoman(-1));
    }

    @ParameterizedTest
    @MethodSource("digits")
    void testDigits(int n, String d) {
        Assertions.assertEquals(d, Task4.convertToRoman(n));
    }
}
