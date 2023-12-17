package edu.project3.SourceTests;

import edu.project3.Sources.GlobSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GlobSourceTest {
    @Test
    void globSourceTest() {
        GlobSource globSource = new GlobSource();
        String result = globSource.getData("**test/**file1*.txt");
        Assertions.assertTrue(result.contains("file1_1") && result.contains("file 1_1") && result.contains("file1")  && result.contains("file 1"));
        result = globSource.getData("**test/**file1_1.txt");
        Assertions.assertTrue(result.contains("file1_1") && result.contains("file 1_1"));
    }
}
