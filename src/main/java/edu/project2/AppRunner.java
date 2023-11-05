package edu.project2;

import edu.project2.Generators.AlgorithmOfAldousBroder;
import edu.project2.Generators.AlgorithmOfKruskal;
import edu.project2.Generators.Generator;
import edu.project2.MazeStructure.Coordinate;
import edu.project2.MazeStructure.Maze;
import edu.project2.Random.AldousBroderRandom;
import edu.project2.Random.KruskalRandom;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import java.util.List;

public final class AppRunner {
    public static void run() {
        int variant;
        int height;
        int width;
        try {
            MessagePrinter.chooseAlgorithmPhrase();
            variant = InputHandler.variantHandler();
            MessagePrinter.askHeight();
            height = InputHandler.sideHandler();
            MessagePrinter.askWidth();
            width = InputHandler.sideHandler();
            Maze maze = null;
            switch (variant) {
                case 1:
                    Generator generator = new AlgorithmOfKruskal(new KruskalRandom());
                    maze = generator.generate(height, width);
                    break;
                case 2:
                    Generator generator1 = new AlgorithmOfAldousBroder(new AldousBroderRandom());
                    maze = generator1.generate(height, width);
                    break;
                default:
                    break;
            }
            if (maze != null) {
                MessagePrinter.printMaze(new MazeRenderer().render(maze));
            }

            MessagePrinter.chooseSearchPhrase();
            variant = InputHandler.variantHandler();

            List<Coordinate> path = null;
            MessagePrinter.startPoint();
            Coordinate start;
            MessagePrinter.inputX();
            int x;
            x = InputHandler.xAndYHandler();
            MessagePrinter.inputY();
            int y;
            y = InputHandler.xAndYHandler();
            start = new Coordinate(x, y);
            MessagePrinter.endPoint();
            MessagePrinter.inputX();
            x = InputHandler.xAndYHandler();
            MessagePrinter.inputY();
            y = InputHandler.xAndYHandler();
            Coordinate end;
            end = new Coordinate(x, y);
            switch (variant) {
                case 1:
                    BFSSolver bfsSolver = new BFSSolver();
                    path = bfsSolver.solve(maze, start, end);
                    break;
                case 2:
                    DFSSolver dfsSolver = new DFSSolver();
                    path = dfsSolver.solve(maze, start, end);
                    break;
                default:
                    break;
            }
            if (path != null && maze != null) {
                MessagePrinter.printMaze(new MazeRenderer().render(maze, path));
            }
        } catch (Exception e) {
            MessagePrinter.incorrectInput();
            throw new IllegalArgumentException();
        }
    }

    private AppRunner() {

    }
}
