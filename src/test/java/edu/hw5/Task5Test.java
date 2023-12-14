package edu.hw5;

import edu.hw5.Task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    void isRussianCarNumberTest() {
        Assertions.assertTrue(Task5.isRussianCarNumber("В777ОР069"));
        Assertions.assertTrue(Task5.isRussianCarNumber("B777OP069"));//латынь
        Assertions.assertTrue(Task5.isRussianCarNumber("М123НР777"));
        Assertions.assertTrue(Task5.isRussianCarNumber("А123ВЕ777"));
        Assertions.assertTrue(Task5.isRussianCarNumber("О777ОО177"));

        Assertions.assertFalse(Task5.isRussianCarNumber("В777ОР06969"));
        Assertions.assertFalse(Task5.isRussianCarNumber("АО777ОО177"));
        Assertions.assertFalse(Task5.isRussianCarNumber("123АВЕ777"));
        Assertions.assertFalse(Task5.isRussianCarNumber("А123ВГ77"));
        Assertions.assertFalse(Task5.isRussianCarNumber("А123ВЕ7777"));

    }
}
