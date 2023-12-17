package edu.hw6;

import edu.hw6.Task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Task2Test {

    @Test
    void isUnsuccessfulAttemptToCopyFileTest() {
        Assertions.assertTrue(Task2.isUnsuccessfulAttemptToCopyFile(Paths.get("aboba"), Paths.get("abobaCopy")));
        Path copy = Paths.get("src/test/java/edu/hw6/Task2TestFiles/forCopyTest - копия.txt");
        Assertions.assertFalse(
            Task2.isUnsuccessfulAttemptToCopyFile(
                Paths.get("src/test/java/edu/hw6/Task2TestFiles/forCopyTest.txt"),
                copy
            ));
        try {
            List<String> lines = Files.readAllLines(copy, UTF_8);
            Assertions.assertEquals("copyTest", lines.getFirst());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.deleteIfExists(copy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void cloneFileTest() {
        Assertions.assertThrows(RuntimeException.class, () -> Task2.cloneFile(Paths.get("asfdsf")));
        Path path = Paths.get("src/test/java/edu/hw6/Task2TestFiles/Tinkoff Bank Biggest Secret.txt");
        Path path1 = Paths.get("src/test/java/edu/hw6/Task2TestFiles/Tinkoff Bank Biggest Secret - копия.txt");
        Path path2 = Paths.get("src/test/java/edu/hw6/Task2TestFiles/Tinkoff Bank Biggest Secret - копия (2).txt");
        Path path3 = Paths.get("src/test/java/edu/hw6/Task2TestFiles/Tinkoff Bank Biggest Secret - копия (3).txt");
        Path[] paths = {path, path1, path2, path3};
        try {
            List<String> lines = Files.readAllLines(paths[0], UTF_8);
            for (int i = 0; i < 3; i++) {
                Task2.cloneFile(path);
                List<String> linesCopy = Files.readAllLines(paths[i + 1], UTF_8);
                Assertions.assertTrue(Files.exists(paths[i + 1]));
                Assertions.assertEquals(lines, linesCopy);
            }
            for (int i = 0; i < 3; i++) {
                Files.deleteIfExists(paths[i + 1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
