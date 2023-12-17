package edu.project3.SourceTests;

import edu.project3.Sources.FileSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileSourceTest {

    @Test
    void fileSourceTest() {
        FileSource fileSource = new FileSource();
        String result = "test\ntestik\nuwu";
        Assertions.assertEquals(result, fileSource.getData("src/test/java/edu/project3/TestFiles/forFileSource.txt"));
    }
}
