package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class OutputPrinter {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void guessALetter() {
        LOGGER.info("Угадайте букву:");
    }

    public static void incorrectWord() {
        LOGGER.info("Загадываемое слово имеет некорректную длину");
    }

    public static void incorrectInput() {
        LOGGER.info("Некорректный ввод, введите букву русского алфавита!");
    }

    public static void printMessageAndState(GuessResult guess) {
        LOGGER.info(guess.message());
        LOGGER.info("Слово: " + guess.state());
        if (guess instanceof GuessResult.Win) {
            LOGGER.info("Вы победили!");
        } else if (guess instanceof GuessResult.Defeat) {
            LOGGER.info("Вы проиграли!");
        }
    }

    private OutputPrinter() {
    }
}
