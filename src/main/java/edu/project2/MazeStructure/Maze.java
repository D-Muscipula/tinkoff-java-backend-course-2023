package edu.project2.MazeStructure;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        if (height <= 0 || width <= 0 || grid == null) {
            throw new IllegalArgumentException();
        }
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public Maze(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
