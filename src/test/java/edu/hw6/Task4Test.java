package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task4Test {
    @Test
    void outputStreamCompositionTest() {

        Task4.outputStreamComposition("src/test/java/edu/hw6/test4.txt");
        try {
            Path path = Paths.get("src/test/java/edu/hw6/test4.txt");
            String content = new String(Files.readAllBytes(path));
            Assertions.assertEquals("Programming is learned by writing programs. ― Brian Kernighan", content);
            Files.deleteIfExists(path);
        } catch (IOException ignored) {
        }
    }
}
