package edu.hw3;

import edu.hw3.Task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void commonSituationsTest() {
        Assertions.assertArrayEquals(new String[] {"()", "()", "()"}, Task2.clusterize("()()()"));
        Assertions.assertArrayEquals(
            new String[] {"((()))", "(())", "()", "()", "(()())"},
            Task2.clusterize("((()))(())()()(()())")
        );
        Assertions.assertArrayEquals(new String[] {"((())())", "(()(()()))"}, Task2.clusterize("((())())(()(()()))"));
        Assertions.assertArrayEquals(new String[] {"(())", "(())"}, Task2.clusterize("(())(())"));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize(null));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize("("));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize(")"));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize("(  )"));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize("(!)"));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize("()("));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize("())("));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize(")("));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task2.clusterize("90"));
    }
}
