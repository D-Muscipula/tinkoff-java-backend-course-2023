package edu.project3.SourceTests;

import edu.project3.Sources.MasterOfSources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SourcesTest {
    @Test
    void getDataTest() {
        MasterOfSources masterOfSources = new MasterOfSources();
        String expected = """
            file1_1
            file 1_1""";
        String result = masterOfSources.getData("src/test/java/edu/project3/TestFiles/TestInner/InnerDirectory/file1_1.txt");
        Assertions.assertEquals(expected, result);

        expected = """
            file1
            file 1
            file1_1
            file 1_1""";
        result = masterOfSources.getData("**test/**file1*.txt");
        Assertions.assertEquals(expected, result);
    }
}
