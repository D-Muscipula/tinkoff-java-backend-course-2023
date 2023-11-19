package edu.project3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        String stringArguments = String.join(" ", args);
        try {
            Arguments arguments = Arguments.getCommandFromString(stringArguments);
            AppRunner.run(arguments);
        } catch (Exception e) {
            LOGGER.info("Что-то пошло не так " + e.getMessage());
        }
    }

    private Main() {
    }
}
