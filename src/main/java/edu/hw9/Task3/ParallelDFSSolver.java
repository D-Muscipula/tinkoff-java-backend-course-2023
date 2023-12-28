package edu.hw9.Task3;

import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Solvers.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelDFSSolver implements Solver {
    List<Coordinate> used;
    private Maze maze;

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

        used = new CopyOnWriteArrayList<>();
        this.maze = maze;
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            SolverTask task = new SolverTask(start, end);
            return forkJoinPool.invoke(task).reversed();
        }
    }

    public List<Coordinate> checkAndAddCoordinates(Coordinate coordinate, Coordinate end) {
        List<SolverTask> branches = new ArrayList<>();
        Coordinate coordinateLeft;
        if (coordinate.col() - 1 >= 0
            && maze.getGrid()[coordinate.row()][coordinate.col() - 1].type() != Cell.Type.WALL) {
            coordinateLeft = new Coordinate(coordinate.row(), coordinate.col() - 1);
            if (!used.contains(coordinateLeft)) {
                SolverTask task = new SolverTask(coordinateLeft, end);
                task.fork();
                branches.add(task);
            }
        }
        Coordinate coordinateBottom;
        if (coordinate.row() + 1 < maze.getHeight()
            && maze.getGrid()[coordinate.row() + 1][coordinate.col()].type() != Cell.Type.WALL) {
            coordinateBottom = new Coordinate(coordinate.row() + 1, coordinate.col());
            if (!used.contains(coordinateBottom)) {
                SolverTask task = new SolverTask(coordinateBottom, end);
                task.fork();
                branches.add(task);
            }
        }
        Coordinate coordinateTop;
        if (coordinate.row() - 1 >= 0
            && maze.getGrid()[coordinate.row() - 1][coordinate.col()].type() != Cell.Type.WALL) {
            coordinateTop = new Coordinate(coordinate.row() - 1, coordinate.col());
            if (!used.contains(coordinateTop)) {
                SolverTask task = new SolverTask(coordinateTop, end);
                task.fork();
                branches.add(task);
            }
        }
        Coordinate coordinateRight;
        if (coordinate.col() + 1 < maze.getWidth()
            && maze.getGrid()[coordinate.row()][coordinate.col() + 1].type() != Cell.Type.WALL) {
            coordinateRight = new Coordinate(coordinate.row(), coordinate.col() + 1);
            if (!used.contains(coordinateRight)) {
                SolverTask task = new SolverTask(coordinateRight, end);
                task.fork();
                branches.add(task);
            }
        }

        for (SolverTask branch : branches) {
            List<Coordinate> path = branch.join();
            if (path != null) {
                return path;
            }
        }
        return null;
    }

    protected class SolverTask extends RecursiveTask<List<Coordinate>> {
        private final Coordinate end;
        private final Coordinate current;

        SolverTask(Coordinate current, Coordinate end) {
            this.current = current;
            this.end = end;
        }

        @Override
        protected List<Coordinate> compute() {
            used.add(current);
            if (current.col() == end.col() && current.row() == end.row()) {
                return new ArrayList<>(List.of(new Coordinate(current.row(), current.col())));
            }
            List<Coordinate> path = checkAndAddCoordinates(current, end);
            if (path != null) {
                path.add(new Coordinate(current.row(), current.col()));
                return path;
            }
            return null;
        }
    }
}
