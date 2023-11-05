package edu.project2.Solvers;

import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements Solver {
    ArrayList<Coordinate> used;
    Map<Coordinate, Coordinate> childParent;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        boolean startIsOut = start.col() < 0 || start.col() >= maze.getWidth()
            || start.row() < 0 || start.row() >= maze.getHeight();
        boolean endIsOut = end.col() < 0 || end.col() >= maze.getWidth()
            || end.row() < 0 || end.row() >= maze.getHeight();
        if (startIsOut || endIsOut || maze.getGrid()[start.row()][start.col()].type() == Cell.Type.WALL
        || maze.getGrid()[end.row()][end.col()].type() == Cell.Type.WALL) {
            throw new IllegalArgumentException();
        }
        Queue<Coordinate> queue = new ArrayDeque<>();
        childParent = new HashMap<>();
        used = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            used.add(coordinate);
            if (!coordinate.equals(end)) {
                checkAndAddCoordinates(maze, coordinate, queue);
            }
            if (coordinate.equals(end)) {
                break;
            }
        }
        List<Coordinate> path = new ArrayList<>();
        Coordinate key = end;
        path.add(key);
        while (!path.contains(start)) {
            path.add(childParent.get(key));
            key = childParent.get(key);
        }
        return path.reversed();
    }

    public void checkAndAddCoordinates(Maze maze, Coordinate coordinate, Queue<Coordinate> queue) {
        Coordinate coordinateLeft;
        if (coordinate.col() - 1 >= 0
            && maze.getGrid()[coordinate.row()][coordinate.col() - 1].type() != Cell.Type.WALL) {
            coordinateLeft = new Coordinate(coordinate.row(), coordinate.col() - 1);
            if (!used.contains(coordinateLeft)) {
                queue.add(coordinateLeft);
                childParent.put(coordinateLeft, coordinate);
            }
        }
        Coordinate coordinateBottom;
        if (coordinate.row() + 1 < maze.getHeight()
            && maze.getGrid()[coordinate.row() + 1][coordinate.col()].type() != Cell.Type.WALL) {
            coordinateBottom = new Coordinate(coordinate.row() + 1, coordinate.col());
            if (!used.contains(coordinateBottom)) {
                queue.add(coordinateBottom);
                childParent.put(coordinateBottom, coordinate);
            }
        }
        Coordinate coordinateTop;
        if (coordinate.row() - 1 >= 0
            && maze.getGrid()[coordinate.row() - 1][coordinate.col()].type() != Cell.Type.WALL) {
            coordinateTop = new Coordinate(coordinate.row() - 1, coordinate.col());
            if (!used.contains(coordinateTop)) {
                queue.add(coordinateTop);
                childParent.put(coordinateTop, coordinate);
            }
        }
        Coordinate coordinateRight;
        if (coordinate.col() + 1 < maze.getWidth()
            && maze.getGrid()[coordinate.row()][coordinate.col() + 1].type() != Cell.Type.WALL) {
            coordinateRight = new Coordinate(coordinate.row(), coordinate.col() + 1);
            if (!used.contains(coordinateRight)) {
                queue.add(coordinateRight);
                childParent.put(coordinateRight, coordinate);
            }
        }
    }
}
