package edu.project2.Generators;

import edu.project2.MazeStructure.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
