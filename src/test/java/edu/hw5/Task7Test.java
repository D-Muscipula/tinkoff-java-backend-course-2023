package edu.hw5;

import edu.hw5.Task7.Task7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task7Test {

    @Test
    void functionsContainOnlyOneAndZero() {
        Assertions.assertFalse(Task7.isTheThirdZero("0a0"));
        Assertions.assertFalse(Task7.isTheThirdZero("010b"));
        Assertions.assertFalse(Task7.isTheThirdZero("a000"));

        Assertions.assertFalse(Task7.isTheSameSymbol("0a0"));
        Assertions.assertFalse(Task7.isTheSameSymbol("1b1"));
        Assertions.assertFalse(Task7.isTheSameSymbol("a0a"));

        Assertions.assertFalse(Task7.isBetweenOneAndThree("a"));
        Assertions.assertFalse(Task7.isBetweenOneAndThree("0a"));
        Assertions.assertFalse(Task7.isBetweenOneAndThree("a0"));
        Assertions.assertFalse(Task7.isBetweenOneAndThree("a0a"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"010", "010001", "101", "100010", "110", "011", "0010000000", "0", "00"})
    void isTheThirdZero(String s) {
        //System.out.println(s + Task7.isTheThirdZero(s));
        Assertions.assertEquals((s.length() >= 3) && (s.charAt(2) == '0'), Task7.isTheThirdZero(s));
    }

    @ParameterizedTest
    @DisplayName("Разные вариации, в том числе строки из 1 символа")
    @ValueSource(strings = {"010", "010001", "101", "100010", "100", "011", "1", "0"})
    void isTheSameSymbol(String s) {
        Assertions.assertEquals(s.charAt(0) == s.charAt(s.length() - 1), Task7.isTheSameSymbol(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "0", "10", "11", "00", "000", "101", "111", "0101", "1111", "01010", "0010000000"})
    void isBetweenOneAndThree(String s) {
        Assertions.assertEquals((s.length() <= 3 && !s.isEmpty()), Task7.isBetweenOneAndThree(s));
    }
}

