package edu.project3.SourceTests;

import edu.project3.Sources.HttpSource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HttpSourceTest {
    @Test
    void getSourceTest() {
        try {
            HttpSource httpSource = new HttpSource();
            String result = httpSource.getData(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
            int count = result.split("\r\n|\r|\n").length;
            Path path = Path.of("src/test/java/edu/project3/TestFiles/HttpFile.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            Assertions.assertEquals(count, lines.size());
            Assertions.assertEquals(String.join("\n", lines), result);

        } catch (Exception ignored) {

        }
    }
}
