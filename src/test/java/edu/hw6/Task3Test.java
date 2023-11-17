package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.regexContains;

public class Task3Test {
    private static final Path[] paths = {
        Path.of("src/test/java/edu/hw6/task3TestFiles/eJLi3--lVFw.jpg"),
        Path.of("src/test/java/edu/hw6/task3TestFiles/finka.png"),
        Path.of("src/test/java/edu/hw6/task3TestFiles/hello.exe"),
        Path.of("src/test/java/edu/hw6/task3TestFiles/images.jpg"),
        Path.of("src/test/java/edu/hw6/task3TestFiles/main.cpp"),
        Path.of("src/test/java/edu/hw6/task3TestFiles/Roboto.zip"),
        Path.of("src/test/java/edu/hw6/task3TestFiles/vinni-pukh-v-png-900x900.png")
    };
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
    @Test
    void readableAndRegularFileTest() {
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable);
        ArrayList<Path> paths1 = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/test/java/edu/hw6/task3TestFiles"), filter)) {
            entries.forEach(paths1::add);

        } catch (IOException ignored) {

        }
        Assertions.assertEquals(new TreeSet<>(List.of(paths)), new TreeSet<>(paths1));
    }

    @Test
    void largerThanTest() {
        DirectoryStream.Filter<Path> filter = largerThan(2000000);
        ArrayList<Path> paths1 = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/test/java/edu/hw6/task3TestFiles"), filter)) {
            entries.forEach(paths1::add);

        } catch (IOException ignored) {

        }
        Assertions.assertEquals(new ArrayList<>(List.of(paths[5])), paths1);
    }

    @Test
    void regexContainsTest() {
        DirectoryStream.Filter<Path> filter = regexContains("\\.jpg");
        ArrayList<Path> paths1 = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/test/java/edu/hw6/task3TestFiles"), filter)) {
            entries.forEach(paths1::add);

        } catch (IOException ignored) {

        }
        ArrayList<Path> paths2 = new ArrayList<>(){{
            add(paths[0]);
            add(paths[3]);
        }};
        Assertions.assertEquals(new TreeSet<>(paths2), new TreeSet<>(paths1));
    }

    @Test
    void globMatchesTest() {
        DirectoryStream.Filter<Path> filter = globMatches("m???.cpp");
        ArrayList<Path> paths1 = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/test/java/edu/hw6/task3TestFiles"), filter)) {
            entries.forEach(paths1::add);

        } catch (IOException ignored) {

        }
        ArrayList<Path> paths2 = new ArrayList<>(){{
            add(paths[4]);
        }};
        Assertions.assertEquals(paths2, paths1);
    }

    @Test
    void magicNumberTest() {
        DirectoryStream.Filter<Path> filter = magicNumber(0x89, 'P', 'N', 'G');
        ArrayList<Path> paths1 = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/test/java/edu/hw6/task3TestFiles"), filter)) {
            entries.forEach(paths1::add);

        } catch (IOException ignored) {

        }
        ArrayList<Path> paths2 = new ArrayList<>(){{
            add(paths[1]);
            add(paths[6]);
        }};
        Assertions.assertEquals(new TreeSet<>(paths2), new TreeSet<>(paths1));
    }

    @Test
    void AllInAllTest() {


        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(10000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[f]"))
          ;
        ArrayList<Path> paths1 = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/test/java/edu/hw6/task3TestFiles"), filter)) {
            entries.forEach(paths1::add);

        } catch (IOException ignored) {

        }
        ArrayList<Path> paths2 = new ArrayList<>(){{
            add(paths[1]);
        }};
        Assertions.assertEquals(paths2, paths1);
    }
}
