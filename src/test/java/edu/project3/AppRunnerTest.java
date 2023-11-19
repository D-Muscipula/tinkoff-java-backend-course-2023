package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppRunnerTest {
    @Test
    void test() {
        Assertions.assertDoesNotThrow(() -> {
            String[] args = {"--path", "src/test/java/edu/project3/TestFiles/someLogs.txt", "--from 2005-08-31", "--format markdown"};
            String stringArguments = String.join(" ", args);
            Arguments arguments = Arguments.getCommandFromString(stringArguments);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm-ss");
            String now = "Report " + LocalDateTime.now().format(formatter) + ".md";
            String path = "src/main/java/edu/project3/Reports/";
            path += now;
            Files.deleteIfExists(Path.of(path));
            AppRunner.run(arguments);
        });
    }
}
