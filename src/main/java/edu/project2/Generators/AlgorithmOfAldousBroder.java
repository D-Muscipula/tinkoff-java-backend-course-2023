package edu.project2.Generators;

import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.RandomGenerator;
import java.util.ArrayList;
import java.util.Queue;

public class AlgorithmOfAldousBroder implements Generator {
    RandomGenerator randomGenerator;

    public AlgorithmOfAldousBroder(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Maze generate(int height, int width) {
        Cell[][] cells = new Cell[height][width];
        ArrayList<Coordinate> need = new ArrayList<>();
        initCells(cells, need);
        connectCells(cells, need);
        return new Maze(height, width, cells);
    }

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:CyclomaticComplexity"})
    private void connectCells(Cell[][] cells, ArrayList<Coordinate> need) {
        Coordinate currentCoordinate = new Coordinate(0, 0);
        makeCellThePassage(cells, 0, 0);
        Queue<Integer> queue = randomGenerator.generate(3, 10);
        while (!need.isEmpty()) {
            int randomNum = 0;
            if (queue.isEmpty()) {
                queue = randomGenerator.generate(3, 20);
            } else {
                randomNum = queue.poll();
            }
            //Случайно выбирается одна из 4 клеток, окружающих текущую клетку, и соединяется с текущей
            switch (randomNum) {
                case 0:
                    if (currentCoordinate.col() - 2 >= 0
                        &&
                        cells[currentCoordinate.row()][currentCoordinate.col() - 2].type() != Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        makeCellThePassage(cells, currentCoordinate.row(), currentCoordinate.col() - 1);
                        makeCellThePassage(cells, currentCoordinate.row(), currentCoordinate.col() - 2);
                        currentCoordinate = new Coordinate(currentCoordinate.row(), currentCoordinate.col() - 2);
                    } else if (currentCoordinate.col() - 2 >= 0
                        &&
                        cells[currentCoordinate.row()][currentCoordinate.col() - 2].type() == Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        currentCoordinate = new Coordinate(currentCoordinate.row(), currentCoordinate.col() - 2);
                    }
                    break;
                case 1:
                    if (currentCoordinate.row() + 2 < cells.length
                        &&
                        cells[currentCoordinate.row() + 2][currentCoordinate.col()].type() != Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        makeCellThePassage(cells, currentCoordinate.row() + 1, currentCoordinate.col());
                        makeCellThePassage(cells, currentCoordinate.row() + 2, currentCoordinate.col());
                        currentCoordinate = new Coordinate(currentCoordinate.row() + 2, currentCoordinate.col());

                    } else if (currentCoordinate.row() + 2 < cells.length
                        &&
                        cells[currentCoordinate.row() + 2][currentCoordinate.col()].type() == Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        currentCoordinate = new Coordinate(currentCoordinate.row() + 2, currentCoordinate.col());

                    }
                    break;
                case 2:
                    if (currentCoordinate.row() - 2 >= 0
                        &&
                        cells[currentCoordinate.row() - 2][currentCoordinate.col()].type() != Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        makeCellThePassage(cells, currentCoordinate.row() - 1, currentCoordinate.col());
                        makeCellThePassage(cells, currentCoordinate.row() - 2, currentCoordinate.col());
                        currentCoordinate = new Coordinate(currentCoordinate.row() - 2, currentCoordinate.col());
                    } else if (currentCoordinate.row() - 2 >= 0
                        &&
                        cells[currentCoordinate.row() - 2][currentCoordinate.col()].type() == Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        currentCoordinate = new Coordinate(currentCoordinate.row() - 2, currentCoordinate.col());
                    }
                    break;
                case 3:
                    if (currentCoordinate.col() + 2 < cells[0].length
                        &&
                        cells[currentCoordinate.row()][currentCoordinate.col() + 2].type() != Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        makeCellThePassage(cells, currentCoordinate.row(), currentCoordinate.col() + 1);
                        makeCellThePassage(cells, currentCoordinate.row(), currentCoordinate.col() + 2);
                        currentCoordinate = new Coordinate(currentCoordinate.row(), currentCoordinate.col() + 2);
                    } else if (currentCoordinate.col() + 2 < cells[0].length
                        &&
                        cells[currentCoordinate.row()][currentCoordinate.col() + 2].type() == Cell.Type.PASSAGE) {
                        need.remove(currentCoordinate);
                        currentCoordinate = new Coordinate(currentCoordinate.row(), currentCoordinate.col() + 2);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    private void initCells(Cell[][] cells, ArrayList<Coordinate> need) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j, Cell.Type.WALL);
                if (i % 2 == 0 && j % 2 == 0) {
                    need.add(new Coordinate(i, j));
                }
            }
        }
    }

    private void makeCellThePassage(Cell[][] cells, int row, int col) {
        cells[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
    }
}
