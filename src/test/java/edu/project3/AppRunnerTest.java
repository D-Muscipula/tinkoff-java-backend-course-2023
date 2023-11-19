package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppRunnerTest {
    @Test
    void test() {
        Assertions.assertDoesNotThrow(() -> {
            String[] args = {"--path", "src/test/java/edu/project3/TestFiles/someLogs.txt", "--from 2005-08-31", "--format markdown"};
            String stringArguments = String.join(" ", args);
            Arguments arguments = Arguments.getCommandFromString(stringArguments);
            AppRunner.run(arguments);
        });
    }
}
