package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    void RotateRightCommonSituations() {
        assert (Task7.rotateRight(1,1) == 1);
        assert (Task7.rotateRight(8,1) == 4);
        assert (Task7.rotateRight(15,2) == 15);
        assert (Task7.rotateRight(14,1) == 7);
        assert (Task7.rotateRight(14,2) == 11);
        assert (Task7.rotateRight(14,3) == 13);
    }

    @Test
    void RotateLeftCommonSituations() {
        assert (Task7.rotateLeft(16,1) == 1);
        assert (Task7.rotateLeft(17,2) == 6);
        assert (Task7.rotateLeft(17,3) == 12);
    }

    @Test
    @DisplayName("Если сдвиг больше количества цифр в двоичной форме")
    void RotateLeftRotateRightBigShift() {
        assert (Task7.rotateRight(14,5) == 7);
        assert (Task7.rotateRight(14,6) == 11);
        assert (Task7.rotateRight(14,7) == 13);
        assert (Task7.rotateLeft(16,6) == 1);
        assert (Task7.rotateLeft(17,7) == 6);
        assert (Task7.rotateLeft(17,8) == 12);
    }

    @Test
    void RotateLeftRotateRightSmallShift(){
        assert (Task7.rotateRight(14,0) == 14);
        assert (Task7.rotateLeft(16,0) == 16);
        assert (Task7.rotateRight(14,-1) == 14);
        assert (Task7.rotateLeft(16,-10) == 16);
    }
}
