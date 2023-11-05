package edu.project2.Solvers;

import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
