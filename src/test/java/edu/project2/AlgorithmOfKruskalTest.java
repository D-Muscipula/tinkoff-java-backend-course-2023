package edu.project2;

import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Maze;
import org.junit.jupiter.api.Test;

public class AlgorithmOfKruskalTest {
    @Test
    void test() {
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal();
         Maze maze = algorithmOfKruskal.generate(5,5);
    }
}
