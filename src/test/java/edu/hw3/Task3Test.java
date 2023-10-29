package edu.hw3;

import edu.hw3.Task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class Task3Test {
    @Test
    void commonSituationsTest() {
        Assertions.assertEquals(new HashMap<>() {
            {
                put("код", 3);
                put("bug", 1);
            }
        }, Task3.freqDict(new String[] {"код", "код", "код", "bug"}));
        Assertions.assertEquals(new HashMap<>() {
            {
                put(1, 2);
                put(2, 2);
            }
        }, Task3.freqDict(new Integer[] {1, 1, 2, 2}));

        Assertions.assertEquals(new HashMap<>() {
            {
                put("a", 2);
                put("bb", 2);
            }
        }, Task3.freqDict(new String[] {"a", "bb", "a", "bb"}));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Task3.freqDict(null));
    }
}
