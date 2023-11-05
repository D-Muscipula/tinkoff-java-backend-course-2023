package edu.project2;

import edu.project2.Generators.AlgorithmOfAldousBroder;
import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.AldousBroderRandom;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RendererTest {
    private final static Logger LOGGER = LogManager.getLogger();
    @Test
    void renderTest() {

        AlgorithmOfKruskal algorithmOfKruskal = new AlgorithmOfKruskal();
        AlgorithmOfAldousBroder algorithmOfAldousBroder = new AlgorithmOfAldousBroder(new AldousBroderRandom());
        BFSSolver bfsSolver = new BFSSolver();
        DFSSolver dfsSolver = new DFSSolver();
        Maze maze = algorithmOfKruskal.generate(50,50);
        Maze maze1 = algorithmOfAldousBroder.generate(1,49);
        Renderer renderer = new MazeRenderer();
        //System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze1));
        List<Coordinate> path = bfsSolver.solve(maze, new Coordinate(0, 0), new Coordinate(48, 48));
        List<Coordinate> pathDFS = dfsSolver.solve(maze, new Coordinate(0, 0), new Coordinate(48, 48));
       // List<Coordinate> path1 = bfsSolver.solve(maze1, new Coordinate(0, 0), new Coordinate(48, 48));
       // List<Coordinate> pathDFS1 = dfsSolver.solve(maze1, new Coordinate(0, 0), new Coordinate(48, 48));
        //System.out.println(renderer.render(maze, path));
        //System.out.println(renderer.render(maze, pathDFS));
        //LOGGER.info('\n' +renderer.render(maze, path1));
        //System.out.println(renderer.render(maze, path1));
       // System.out.println(renderer.render(maze, pathDFS1));
    }
}
