package edu.project2;

import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.KruskalRandom;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class DFSSolverTest {
    @Test
    void solveTest() {
        DFSSolver dfsSolver = new DFSSolver();
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new AlgorithmOfKruskalTest.OurShuffle());
        Maze maze = algorithmOfKruskal.generate(5, 5);
        List<Coordinate> coordinates  = dfsSolver.solve(maze,new Coordinate(0, 0),new Coordinate(4,4));
        List<Coordinate> result = new ArrayList<>(){{
            add(new Coordinate(0,0));
            add(new Coordinate(0,1));
            add(new Coordinate(0,2));
            add(new Coordinate(1,2));
            add(new Coordinate(2,2));
            add(new Coordinate(2,3));
            add(new Coordinate(2,4));
            add(new Coordinate(3,4));
            add(new Coordinate(4,4));
        }};
        Assertions.assertEquals(result, coordinates);
    }

    @Test
    void checkWithBFS() {
        DFSSolver dfsSolver = new DFSSolver();
        BFSSolver bfsSolver = new BFSSolver();
        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal(new KruskalRandom());
        Maze maze = algorithmOfKruskal.generate(51, 53);
        List<Coordinate> coordinates  = dfsSolver.solve(maze,new Coordinate(0, 0),new Coordinate(50,52));
        List<Coordinate> coordinates1 = bfsSolver.solve(maze,new Coordinate(0, 0),new Coordinate(50,52));
        Assertions.assertEquals(coordinates, coordinates1);
    }
}
