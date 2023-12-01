package edu.project2.Generators;

import edu.project2.MazeStructure.Cell;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Edge;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.Shuffle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AlgorithmOfKruskal implements Generator {
    private final Shuffle shuffle;

    public AlgorithmOfKruskal(Shuffle shuffle) {
        if (shuffle == null) {
            throw new IllegalArgumentException();
        }
        this.shuffle = shuffle;
    }

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        Cell[][] cells = new Cell[height][width];
        ArrayList<Edge> edges = new ArrayList<>();
        HashMap<Coordinate, Set<Coordinate>> coordinateSetHashMap = new HashMap<>();
        initCellsEdgesAndMap(cells, edges, coordinateSetHashMap);
        //Перемешиваем ребра в случайном порядке
        shuffle.shuffle(edges);
        connectTheEdges(edges, coordinateSetHashMap, cells);
        return new Maze(height, width, cells);
    }

    private void connectTheEdges(
        ArrayList<Edge> edges,
        HashMap<Coordinate, Set<Coordinate>> coordinateSetHashMap,
        Cell[][] cells
    ) {
        for (Edge edge : edges) {
            Coordinate firstNode = edge.start();
            Coordinate middle = edge.middle();
            Coordinate secondNode = edge.end();
            if (coordinateSetHashMap.get(firstNode) != coordinateSetHashMap.get(secondNode)) {
                //Если множества разные, то в первое записываем все элементы второго и клетку между ними(middle)
                coordinateSetHashMap.get(firstNode).add(middle);
                coordinateSetHashMap.get(firstNode).addAll(coordinateSetHashMap.get(secondNode));
                Set<Coordinate> coordinateSetOfSecondNode = coordinateSetHashMap.get(secondNode);
                //Присваиваем по ключу каждого элемента множества второго узла set первого узла
                for (var element
                        : coordinateSetOfSecondNode) {
                    coordinateSetHashMap.put(element, coordinateSetHashMap.get(firstNode));
                }
                //"Перекрашиваем" ячейки
                makeCellThePassage(cells, firstNode.row(), firstNode.col());
                makeCellThePassage(cells, middle.row(), middle.col());
                makeCellThePassage(cells, secondNode.row(), secondNode.col());
            }
        }
    }

    private void makeCellThePassage(Cell[][] cells, int row, int col) {
        cells[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
    }

    public void initCellsEdgesAndMap(
        Cell[][] cells,
        ArrayList<Edge> edges,
        HashMap<Coordinate, Set<Coordinate>> coordinateSetHashMap
    ) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j, Cell.Type.WALL);
                if (i % 2 == 0 && j % 2 == 0) {
                    Coordinate coordinateStart = new Coordinate(i, j);
                    Set<Coordinate> coordinateSet = new HashSet<>() {{
                        add(coordinateStart);
                    }};
                    coordinateSetHashMap.put(coordinateStart, coordinateSet);
                    if (j + 2 < cells[i].length) {
                        Coordinate coordinateMiddleOfRight = new Coordinate(i, j + 1);
                        Coordinate coordinateEndOfRight = new Coordinate(i, j + 2);
                        edges.add(new Edge(coordinateStart, coordinateMiddleOfRight, coordinateEndOfRight));
                    }

                    if (i + 2 < cells.length) {
                        Coordinate coordinateMiddleOfBottom = new Coordinate(i + 1, j);
                        Coordinate coordinateEndOfBottom = new Coordinate(i + 2, j);
                        edges.add(new Edge(coordinateStart, coordinateMiddleOfBottom, coordinateEndOfBottom));
                    }
                }
            }
        }
    }
}
