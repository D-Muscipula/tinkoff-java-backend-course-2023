package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void minutesToSecondsTestCommonSituations() {
        Assertions.assertEquals(Task1.minutesToSeconds("01:00"), 60);
        Assertions.assertEquals(Task1.minutesToSeconds("13:56"), 836);
        Assertions.assertEquals(Task1.minutesToSeconds("100:59"), 6059);
    }

    @Test
    void minutesToSecondsTestSecondsAreIncorrect() {
        Assertions.assertEquals(Task1.minutesToSeconds("01:0"), -1);
        Assertions.assertEquals(Task1.minutesToSeconds("13:001"), -1);
        Assertions.assertEquals(Task1.minutesToSeconds("10:60"), -1);
    }

    @Test
    void minutesToSecondsTestSomethingElseIsIncorrect() {
        Assertions.assertEquals(Task1.minutesToSeconds("0100"), -1);
        Assertions.assertEquals(Task1.minutesToSeconds("-13:001"), -1);
        Assertions.assertEquals(Task1.minutesToSeconds("1n:60"), -1);
    }
}
