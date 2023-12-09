package edu.hw9;

import edu.hw9.Task2.TreeHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void directorySearchTest() {
        List<File> dirs = TreeHandler.getDirectories(new File("src/test/java/edu/hw9/testDir"), 3);
        ArrayList<File> files = new ArrayList<>() {{
            add(new File("src/test/java/edu/hw9/testDir/testDirSecond"));
        }};
        Assertions.assertEquals(files, dirs);

        dirs = TreeHandler.getDirectories(new File("src/test/java/edu/hw9/testDir"), 1);
        Collections.sort(dirs);
        files = new ArrayList<>() {{
            add(new File("src/test/java/edu/hw9/testDir/testDirSecond"));
            add(new File("src/test/java/edu/hw9/testDir"));
        }};
        Collections.sort(files);
        Assertions.assertEquals(files, dirs);
        Assertions.assertThrows(IllegalArgumentException.class, () -> TreeHandler.getDirectories(new File("asdf"), -1));
    }

    @Test
    void predicateTest() {
        List<File> result =
            TreeHandler.getFiles(new File("src/test/java/edu/hw9/testDir"), TreeHandler.getSizePredicate(0));
        ArrayList<File> files = new ArrayList<>() {{
            add(new File("src/test/java/edu/hw9/testDir/testDirSecond/aasdf.txt"));
            add(new File("src/test/java/edu/hw9/testDir/testDirSecond/aboba.txt"));
        }};
        Collections.sort(result);
        Collections.sort(files);
        Assertions.assertEquals(result,files);


        result =
            TreeHandler.getFiles(new File("src/test/java/edu/hw9/testDir"), TreeHandler.getExtensionPredicate("png"));
        files = new ArrayList<>() {{
            add(new File("src/test/java/edu/hw9/testDir/testDirSecond/picture.png"));
            add(new File("src/test/java/edu/hw9/testDir/testDirSecond/xmmmm.png"));
        }};
        Collections.sort(result);
        Collections.sort(files);
        Assertions.assertEquals(result,files);
    }
}
