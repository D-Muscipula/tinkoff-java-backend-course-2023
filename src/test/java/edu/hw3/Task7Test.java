package edu.hw3;

import edu.hw3.Task7.Task7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

public class Task7Test {
    @Test
    void testTree() {
        TreeMap<String, String> tree = new TreeMap<>(Task7.getComparator());
        tree.put(null, "test");
        Assertions.assertTrue(tree.containsKey(null));
    }

    @Test
    void testTreeNormalKeyIsBefore() {
        TreeMap<String, String> tree = new TreeMap<>(Task7.getComparator());
        tree.put("a", "test");
        tree.put(null, "test");
        Assertions.assertTrue(tree.containsKey(null));
    }

    @Test
    void testTreeNormalKeysAreBeforeAndAfter() {
        TreeMap<String, String> tree = new TreeMap<>(Task7.getComparator());
        tree.put("a", "test");
        tree.put(null, "test");
        tree.put("b", "test");
        Assertions.assertTrue(tree.containsKey(null));
    }
}
