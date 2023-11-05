package edu.project2;

import edu.project2.Generators.AlgorithmOfAldousBroder;
import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Queue;

public class AlgorithmOfAldousBroderTest {

    static class OurRandom implements RandomGenerator{
        @Override
        public Queue<Integer> generate(int bound, int quantity) {
            return new ArrayDeque<>(){{
                add(3);
                add(3);
                add(0);
                add(1);
                add(0);
                add(1);
                add(2);
                add(3);
                add(1);
                add(2);
                add(3);
                add(1);
            }};
        }
    }
    @Test
    void ourRandomTest() {
        AlgorithmOfAldousBroder algorithmOfAldousBroder = new AlgorithmOfAldousBroder(new OurRandom());
        Maze maze = algorithmOfAldousBroder.generate(5,5);
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
}
