package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MessagePrinter {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void chooseAlgorithmPhrase() {
        String message = """
            Выберите алгоритм генерации:
            1 - Алгоритм Крускала
            2 - Алгоритм Олдоса-Бродера""";
        LOGGER.info(message);
    }

    public static void chooseSearchPhrase() {
        String message = """
            Выберите алгоритм генерации:
            1 - BFS
            2 - DFS""";
        LOGGER.info(message);
    }

    public static void incorrectInput() {
        LOGGER.info("Ввод некорректен");
    }

    public static void askHeight() {
        LOGGER.info("Введите высоту");
    }

    public static void askWidth() {
        LOGGER.info("Введите ширину");
    }

    public static void startPoint() {
        LOGGER.info("Введите начальную точку");
    }

    public static void endPoint() {
        LOGGER.info("Введите конечную точку");
    }

    public static void inputX() {
        LOGGER.info("Введите индекс строки");
    }

    public static void inputY() {
        LOGGER.info("Введите индекс колонки");
    }

    public static void printMaze(String maze) {
        LOGGER.info('\n' + maze);
    }

    private MessagePrinter() {
    }
}
