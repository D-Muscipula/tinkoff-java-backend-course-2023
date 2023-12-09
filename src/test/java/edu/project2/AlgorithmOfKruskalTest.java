package edu.project2;

import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Edge;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.KruskalRandom;
import edu.project2.Random.Shuffle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AlgorithmOfKruskalTest {
    public static class WithOutShuffle implements Shuffle {
        @Override
        public void shuffle(ArrayList<Edge> edges) {

        }
    }

    public static class OurShuffle implements Shuffle {
        // 0 - 3
        // 1 - 5
        // 4 - 7
        @Override
        public void shuffle(ArrayList<Edge> edges) {
            Edge temp = edges.getFirst();
            edges.set(0, edges.get(3));
            edges.set(3, temp);
            temp = edges.get(1);
            edges.set(1, edges.get(5));
            edges.set(5, temp);
            temp = edges.get(4);
            edges.set(4, edges.get(7));
            edges.set(7, temp);
        }
    }

    @Test
    void withOutShuffleTest() {
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new WithOutShuffle());
        Maze maze = algorithmOfKruskal.generate(5, 5);
        Cell[][] result = new Cell[][]
            {
                {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE),
                    new Cell(0, 2, Cell.Type.PASSAGE), new Cell(0, 3, Cell.Type.PASSAGE),
                    new Cell(0, 4, Cell.Type.PASSAGE)},
                {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.WALL),
                    new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.WALL),
                    new Cell(1, 4, Cell.Type.PASSAGE)},
                {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL),
                    new Cell(2, 2, Cell.Type.PASSAGE), new Cell(2, 3, Cell.Type.WALL),
                    new Cell(2, 4, Cell.Type.PASSAGE)},
                {new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.WALL),
                    new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.WALL),
                    new Cell(3, 4, Cell.Type.PASSAGE)},
                {new Cell(4, 0, Cell.Type.PASSAGE), new Cell(4, 1, Cell.Type.WALL),
                    new Cell(4, 2, Cell.Type.PASSAGE), new Cell(4, 3, Cell.Type.WALL),
                    new Cell(4, 4, Cell.Type.PASSAGE)}
            };
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Assertions.assertEquals(result[i][j], maze.getGrid()[i][j]);
            }

        }

    }

    @Test
    void ourShuffleTest() {
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new OurShuffle());
        Maze maze = algorithmOfKruskal.generate(5, 5);
        Cell[][] result = new Cell[][]
            {
                {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE),
                    new Cell(0, 2, Cell.Type.PASSAGE), new Cell(0, 3, Cell.Type.PASSAGE),
                    new Cell(0, 4, Cell.Type.PASSAGE)},
                {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.WALL),
                    new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.WALL),
                    new Cell(1, 4, Cell.Type.WALL)},
                {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.PASSAGE),
                    new Cell(2, 2, Cell.Type.PASSAGE), new Cell(2, 3, Cell.Type.PASSAGE),
                    new Cell(2, 4, Cell.Type.PASSAGE)},
                {new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.WALL),
                    new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.WALL),
                    new Cell(3, 4, Cell.Type.PASSAGE)},
                {new Cell(4, 0, Cell.Type.PASSAGE), new Cell(4, 1, Cell.Type.WALL),
                    new Cell(4, 2, Cell.Type.PASSAGE), new Cell(4, 3, Cell.Type.WALL),
                    new Cell(4, 4, Cell.Type.PASSAGE)}
            };
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Assertions.assertEquals(result[i][j], maze.getGrid()[i][j]);
            }

        }
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AlgorithmOfKruskal(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new KruskalRandom());
            algorithmOfKruskal.generate(0, 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new KruskalRandom());
            algorithmOfKruskal.generate(1, 0);
        });
    }
}

