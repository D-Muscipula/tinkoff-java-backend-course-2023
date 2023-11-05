package edu.project2;

import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RendererTest {
    @Test
    void renderTest() {
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new AlgorithmOfKruskalTest.OurShuffle());
        Maze maze = algorithmOfKruskal.generate(5, 5);
        Renderer renderer = new MazeRenderer();
        String mazeString = renderer.render(maze);
        String expected = """
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██
            [30m██[34m██[34m██[34m██[34m██[34m██[30m██
            [30m██[30m██[30m██[34m██[30m██[30m██[30m██
            [30m██[34m██[34m██[34m██[34m██[34m██[30m██
            [30m██[34m██[30m██[34m██[30m██[34m██[30m██
            [30m██[34m██[30m██[34m██[30m██[34m██[30m██
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██""";
        Assertions.assertEquals(expected, mazeString);
        AlgorithmOfKruskal algorithmOfKruskal1 = new AlgorithmOfKruskal(new AlgorithmOfKruskalTest.WithOutShuffle());
        maze = algorithmOfKruskal1.generate(5, 5);
        mazeString = renderer.render(maze);
        expected = """
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██
            [30m██[34m██[34m██[34m██[34m██[34m██[30m██
            [30m██[34m██[30m██[34m██[30m██[34m██[30m██
            [30m██[34m██[30m██[34m██[30m██[34m██[30m██
            [30m██[34m██[30m██[34m██[30m██[34m██[30m██
            [30m██[34m██[30m██[34m██[30m██[34m██[30m██
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██""";
        Assertions.assertEquals(expected, mazeString);
    }

    @Test
    void renderPathTest() {
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new AlgorithmOfKruskalTest.OurShuffle());
        Maze maze = algorithmOfKruskal.generate(5, 5);
        Renderer renderer = new MazeRenderer();
        BFSSolver bfsSolver = new BFSSolver();
        List<Coordinate> coordinates = bfsSolver.solve(maze, new Coordinate(0, 0), new Coordinate(4, 4));
        String mazeString = renderer.render(maze, coordinates);
        String expected = """
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██
            [30m██[32m██[32m██[32m██[34m██[34m██[30m██
            [30m██[30m██[30m██[32m██[30m██[30m██[30m██
            [30m██[34m██[34m██[32m██[32m██[32m██[30m██
            [30m██[34m██[30m██[34m██[30m██[32m██[30m██
            [30m██[34m██[30m██[34m██[30m██[32m██[30m██
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██""";
        Assertions.assertEquals(expected, mazeString);

        AlgorithmOfKruskal algorithmOfKruskal1 = new AlgorithmOfKruskal(new AlgorithmOfKruskalTest.WithOutShuffle());
        maze = algorithmOfKruskal1.generate(5, 5);
        DFSSolver dfsSolver = new DFSSolver();
        List<Coordinate> path = dfsSolver.solve(maze, new Coordinate(0, 0), new Coordinate(4, 4));
        mazeString = renderer.render(maze, path);
        expected = """
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██
            [30m██[32m██[32m██[32m██[32m██[32m██[30m██
            [30m██[34m██[30m██[34m██[30m██[32m██[30m██
            [30m██[34m██[30m██[34m██[30m██[32m██[30m██
            [30m██[34m██[30m██[34m██[30m██[32m██[30m██
            [30m██[34m██[30m██[34m██[30m██[32m██[30m██
            [30m██[30m██[30m██[30m██[30m██[30m██[30m██""";
        Assertions.assertEquals(expected, mazeString);
    }
    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new MazeRenderer().render(null));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new MazeRenderer().render(new Maze(1, 2, null)));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new MazeRenderer().render(new Maze(1, 2, null), new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new MazeRenderer().render(new Maze(1, 2), null));

    }

}
