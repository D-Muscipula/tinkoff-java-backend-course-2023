package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    void RotateRightCommonSituations() {
        Assertions.assertEquals(Task7.rotateRight(1, 1), 1);
        Assertions.assertEquals(Task7.rotateRight(8, 1), 4);
        Assertions.assertEquals(Task7.rotateRight(15, 2), 15);
        Assertions.assertEquals(Task7.rotateRight(14, 1), 7);
        Assertions.assertEquals(Task7.rotateRight(14, 2), 11);
        Assertions.assertEquals(Task7.rotateRight(14, 3), 13);
    }

    @Test
    void RotateLeftCommonSituations() {
        Assertions.assertEquals(Task7.rotateLeft(16, 1), 1);
        Assertions.assertEquals(Task7.rotateLeft(17, 2), 6);
        Assertions.assertEquals(Task7.rotateLeft(17, 3), 12);
    }

    @Test
    @DisplayName("Если сдвиг больше количества цифр в двоичной форме")
    void RotateLeftRotateRightBigShift() {
        Assertions.assertEquals(Task7.rotateRight(14, 5), 7);
        Assertions.assertEquals(Task7.rotateRight(14, 6), 11);
        Assertions.assertEquals(Task7.rotateRight(14, 7), 13);
        Assertions.assertEquals(Task7.rotateLeft(16, 6), 1);
        Assertions.assertEquals(Task7.rotateLeft(17, 7), 6);
        Assertions.assertEquals(Task7.rotateLeft(17, 8), 12);
    }

    @Test
    void RotateLeftRotateRightSmallShift() {
        Assertions.assertEquals(Task7.rotateRight(14, 0), 14);
        Assertions.assertEquals(Task7.rotateLeft(16, 0), 16);
        Assertions.assertEquals(Task7.rotateRight(14, -1), 14);
        Assertions.assertEquals(Task7.rotateLeft(16, -10), 16);
    }
}
