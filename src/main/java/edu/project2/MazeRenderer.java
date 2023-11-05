package edu.project2;

import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import java.util.List;

public class MazeRenderer implements Renderer {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String CELL = "\u2588\u2588";
    private static final String PASSAGE = ANSI_BLUE + CELL;
    private static final String WALL = ANSI_BLACK + CELL;
    private static final String PATH = ANSI_GREEN + CELL;
//⬛

    @Override
    public String render(Maze maze) {
        StringBuilder mazeString = new StringBuilder();
        String wall = String.valueOf(WALL);
        /*mazeString.append("  ");
        for (int i = 0; i < maze.getWidth(); i++) {
            mazeString.append(i);
            if (i < 10) {
                mazeString.append(" ");
            }
        }
        mazeString.append("\n\n");*/
        for (int i = 0; i < maze.getWidth() + 1; i++) {
            mazeString.append(WALL);

        }
        if (maze.getWidth() % 2 != 0) {
            mazeString.append(WALL);
        }
        mazeString.append('\n');
        for (int i = 0; i < maze.getHeight(); i++) {
            mazeString.append(WALL);
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getGrid()[i][j].type() == Cell.Type.PASSAGE) {
                    mazeString.append(PASSAGE);
                } else {
                    mazeString.append(wall);
                }
            }
            if (maze.getWidth() % 2 != 0) {
                mazeString.append(wall);
            }
            mazeString.append('\n');
        }
        for (int i = 0; i < maze.getWidth() + 1 && maze.getHeight() % 2 != 0; i++) {
            mazeString.append(WALL);
        }
        if (maze.getWidth() % 2 != 0 && maze.getHeight() % 2 != 0) {
            mazeString.append(WALL);
        }
        return mazeString.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder mazeString = new StringBuilder();
        String wall = String.valueOf(WALL);
        for (int i = 0; i < maze.getWidth() + 1; i++) {
            mazeString.append(WALL);

        }
        if (maze.getWidth() % 2 != 0) {
            mazeString.append(WALL);
        }
        mazeString.append('\n');
        for (int i = 0; i < maze.getHeight(); i++) {
            mazeString.append(WALL);
            for (int j = 0; j < maze.getWidth(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                if (path.contains(coordinate)) {
                    mazeString.append(PATH);
                } else if (maze.getGrid()[i][j].type() == Cell.Type.PASSAGE) {
                    mazeString.append(PASSAGE);
                } else {
                    mazeString.append(wall);
                }
            }
            if (maze.getWidth() % 2 != 0) {
                mazeString.append(wall);
            }
            mazeString.append('\n');
        }
        for (int i = 0; i < maze.getWidth() + 1 && maze.getHeight() % 2 != 0; i++) {
            mazeString.append(WALL);
        }
        if (maze.getWidth() % 2 != 0 && maze.getHeight() % 2 != 0) {
            mazeString.append(WALL);
        }
        return mazeString.toString();
    }
}
