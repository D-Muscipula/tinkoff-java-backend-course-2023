package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Task1Test {
    @Test
    void mapTest() {
        File file = new File("src/test/java/edu/hw6/Task1TestFiles/map.txt");
        String content = """
            abc:a
            b:ab
            c:abc
            d:a
            """;
        Path path = Paths.get("src/test/java/edu/hw6/Task1TestFiles/map.txt");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(content);
            printWriter.close();
            //Конструктор с path
            DiskMap diskMap = new DiskMap(path);
            Assertions.assertEquals("a", diskMap.get("abc"));
            Assertions.assertEquals("ab", diskMap.get("b"));
            Assertions.assertEquals("abc", diskMap.get("c"));
            Assertions.assertEquals("a", diskMap.get("d"));
            //Удаление. Происходит и в мапе, и в файле
            diskMap.remove("b");
            Assertions.assertFalse(diskMap.containsKey("b"));
            List<String> lines = Files.readAllLines(path, UTF_8);
            Assertions.assertTrue(lines.contains("abc:a"));
            Assertions.assertTrue(lines.contains("c:abc"));
            Assertions.assertTrue(lines.contains("d:a"));
            //Очиска. И в мапе, и в файле
            diskMap.clear();
            Assertions.assertTrue(diskMap.isEmpty());
            lines = Files.readAllLines(path, UTF_8);
            Assertions.assertTrue(lines.isEmpty());
            //При вставке в файле происходит дозапись. Если дублируется, то в файл перезаписывается.
            diskMap.put("a", "abc");
            diskMap.put("a", "aboba");
            Assertions.assertEquals("aboba", diskMap.get("a"));
            lines = Files.readAllLines(path, UTF_8);
            Assertions.assertEquals(1, lines.size());
            Assertions.assertEquals("a:aboba", lines.getFirst());
            Path path1 = Paths.get("src/test/java/edu/hw6/Task1TestFiles/map1.txt");
            //Конструктор, копирующий другую мапу
            DiskMap diskMapCopy = new DiskMap(diskMap, path1);
            Assertions.assertEquals("aboba", diskMapCopy.get("a"));
            lines = Files.readAllLines(path1, UTF_8);
            Assertions.assertEquals(1, lines.size());
            Assertions.assertEquals("a:aboba", lines.getFirst());
        } catch (Exception ignored) {
        }
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DiskMap(10, Path.of("asdfsf")));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DiskMap(Paths.get("src/test/java/edu/hw6/Task1TestFiles/errorMap.txt")));

    }

}
