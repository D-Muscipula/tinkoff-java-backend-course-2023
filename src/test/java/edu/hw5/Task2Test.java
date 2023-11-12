package edu.hw5;

import edu.hw5.Task2.Task2;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void getFridaysOf13thTest() {
        String[] strings = Task2.getFridaysOf13th(1925);
        Assertions.assertEquals("[1925-02-13, 1925-03-13, 1925-11-13]", Arrays.toString(strings));
        strings = Task2.getFridaysOf13th(2024);
        Assertions.assertEquals("[2024-09-13, 2024-12-13]", Arrays.toString(strings));
        strings = Task2.getFridaysOf13th(2023);
        Assertions.assertEquals("[2023-01-13, 2023-10-13]", Arrays.toString(strings));
    }

    @Test
    void getNextFridayOf13th() {
        Assertions.assertEquals("2024-09-13", Task2.getNextFridayOf13th("11/08/23"));
        Assertions.assertEquals("2024-09-13", Task2.getNextFridayOf13th("10/13/2023"));
        Assertions.assertEquals("2024-12-13", Task2.getNextFridayOf13th("2024-09-13"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.getNextFridayOf13th("esf"));
    }
}
