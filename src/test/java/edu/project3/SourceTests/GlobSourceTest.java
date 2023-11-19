package edu.project3.SourceTests;

import edu.project3.Sources.GlobSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GlobSourceTest {
    @Test
    void globSourceTest() {
        GlobSource globSource = new GlobSource();
        String expected = """
            file1
            file 1
            file1_1
            file 1_1""";
        String result = globSource.getData("**test/**file1*.txt");
        Assertions.assertEquals(expected, result);

        expected = """
            file1_1
            file 1_1""";
        result = globSource.getData("**test/**file1_1.txt");
        Assertions.assertEquals(expected, result);
    }
}
