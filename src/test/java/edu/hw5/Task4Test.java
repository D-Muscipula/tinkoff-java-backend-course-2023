package edu.hw5;

import edu.hw5.Task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task4Test {
    @ParameterizedTest
    @ValueSource(strings = {"", "sadgsgd", "1", "0~", "1!0", "1@1", "011#", "$110", "101sd%fs", "1^11", "0&101",
        "1*111",
        "0|1010", "10100~!@#$%^&*|10101"})
    void isContainingSymbol(String s) {
        boolean content = s.contains("~") || s.contains("!") || s.contains("@") || s.contains("#") || s.contains("$");
        content =
            content || s.contains("%") || s.contains("^") || s.contains("&") || s.contains("*") || s.contains("|");
        Assertions.assertEquals(content, Task4.isContainingSymbol(s));
    }
}
