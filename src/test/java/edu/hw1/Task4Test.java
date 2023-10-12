package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void fixStringCommon() {
        Assertions.assertEquals(Task4.fixString("123456"), "214365");
        Assertions.assertEquals(Task4.fixString("hTsii  s aimex dpus rtni.g"), "This is a mixed up string.");
        Assertions.assertEquals(Task4.fixString("badce"), "abcde");
    }

    @Test
    void fixStringEmptyAndNull() {
        Assertions.assertTrue(Task4.fixString("").isEmpty());
        Assertions.assertEquals(Task4.fixString(" "), " ");
        Assertions.assertNull(Task4.fixString(null));
    }

}
