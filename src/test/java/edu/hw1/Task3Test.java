package edu.hw1;

import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    void isNestableCommonSituations() {
        assert (Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}));
        assert (Task3.isNestable(new int[] {3, 1}, new int[] {4, 0}));
        assert (!Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
        assert (!Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));
    }

    @Test
    void isNestableSmallArrays() {
        assert (!Task3.isNestable(new int[] {1}, new int[] {2}));
        assert (!Task3.isNestable(new int[] {2}, new int[] {1}));
        assert (!Task3.isNestable(new int[] {}, new int[] {1, 2, 3}));
    }
    @Test
    void isNestableNull() {
        assert (!Task3.isNestable(new int[] {2}, null));
    }

}
