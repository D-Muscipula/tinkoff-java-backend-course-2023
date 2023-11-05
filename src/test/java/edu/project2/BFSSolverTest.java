package edu.project2;

import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Solvers.BFSSolver;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BFSSolverTest {
    @Test
    void solveTest() {
        BFSSolver bfsSolver = new BFSSolver();
        AlgorithmOfKruskal algorithmOfKruskal= new AlgorithmOfKruskal();
        Maze maze = algorithmOfKruskal.generate(3, 3);
        List<Coordinate> coordinates  = bfsSolver.solve(maze,new Coordinate(0, 0),new Coordinate(2,2));
        System.out.println(coordinates);
        System.out.println();
    }
}
