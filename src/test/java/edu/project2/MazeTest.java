package edu.project2;

import edu.project2.MazeStructure.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MazeTest {
    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Maze(0, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Maze(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Maze(1, 1, null));
        Assertions.assertDoesNotThrow( () -> {
            new Maze(1, 1);
        });
    }
}
