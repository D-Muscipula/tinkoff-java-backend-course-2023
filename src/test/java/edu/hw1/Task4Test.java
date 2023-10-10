package edu.hw1;

import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void fixStringCommon() {
        assert (Task4.fixString("123456").equals("214365"));
        assert (Task4.fixString("hTsii  s aimex dpus rtni.g").equals("This is a mixed up string."));
        assert (Task4.fixString("badce").equals("abcde"));
    }

    @Test
    void fixStringEmptyAndNull() {
        assert (Task4.fixString("").isEmpty());
        assert (Task4.fixString(" ").equals(" "));
        assert (Task4.fixString(null) == null);
    }

}
