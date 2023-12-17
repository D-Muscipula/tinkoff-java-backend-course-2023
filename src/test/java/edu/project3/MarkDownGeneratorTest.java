package edu.project3;

import edu.project3.ReportGenerators.MarkdownGenerator;
import edu.project3.Sources.MasterOfSources;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkDownGeneratorTest {
    @Test
    void generateTest() {
        MasterOfSources.paths = new ArrayList<>();
        String data = new MasterOfSources().getData("src/test/java/edu/project3/TestFiles/someLogs.txt");
        Stream<LogRecord> logRecordStream = LogParser.getStreamOfRecords(data);
        LocalDateTime from = LocalDateTime.of(2023, 11, 15, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 11, 30, 10, 0);
        LogReport logReport = new LogReport(from, to);
        logRecordStream.forEach(logReport::updateStatistics);
        logReport.generateStatistics();
        Path path1 = Path.of("src/test/java/edu/project3/TestFiles/log.md");
        MarkdownGenerator markdownGenerator = new MarkdownGenerator(path1);
        markdownGenerator.generateReport(logReport);
        Assertions.assertTrue(Files.exists(path1));

        try {
            Assertions.assertEquals(content, new String(Files.readAllBytes(path1)));
            Files.delete(path1);
        } catch (IOException ignored) {
        }

    }

    String content = """
#### Общая информация
| Метрика| Значение  |
| ---- | ----- |
| Файл(-ы) | someLogs.txt |
| Начальная дата | 15.11.2023 10:00 |
| Конечная дата | 30.11.2023 10:00 |
| Количество запросов | 7 |
| Средний размер ответа | 1173b |


#### Запрашиваемые ресурсы
| Ресурс| Количество  |
| ---- | ----- |
| aboba | 2 |
| another_aboba_with_another_name | 1 |


#### Коды ответа
| Код| Имя| Количество |
| --- | ----- | ----- |
| 200 | OK | 4 |
| 302 | Moved Temporarily (Found) | 1 |
| 404 | Not Found | 1 |
| 503 | Service Unavailable | 1 |


#### Методы
| Метод| Количество  |
| ---- | ----- |
| GET | 3 |
| PATCH | 2 |
| POST | 1 |
| PUT | 1 |


#### Количество ошибок по дням
| День| Количество ошибок  |
| ---- | ----- |
| 22.11.2023 | 1 |
| 23.11.2023 | 1 |
 """;
}
