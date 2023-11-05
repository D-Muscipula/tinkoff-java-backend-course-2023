package edu.project2;

import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Maze;
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
}
