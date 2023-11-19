package edu.project3;

import edu.project3.Sources.MasterOfSources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class LogReportTest {
    @Test
    void logReportTest() {
        String data = new MasterOfSources().getData("src/test/java/edu/project3/TestFiles/someLogs.txt");
        Stream<LogRecord> logRecordStream = LogParser.getStreamOfRecords(data);
        LocalDateTime from = LocalDateTime.of(2023,11,15,10,0);
        LocalDateTime to = LocalDateTime.of(2023,11,30,10,0);
        LogReport logReport = new LogReport(from, to);
        logRecordStream.forEach(logReport::updateStatistics);
        logReport.generateStatistics();
        Assertions.assertEquals("someLogs.txt", logReport.commonInformation.get("Файл(-ы)"));
        Assertions.assertEquals("15.11.2023 10:00", logReport.commonInformation.get("Начальная дата"));
        Assertions.assertEquals("30.11.2023 10:00", logReport.commonInformation.get("Конечная дата"));
        Assertions.assertEquals("7", logReport.commonInformation.get("Количество запросов"));
        Assertions.assertEquals("1173b", logReport.commonInformation.get("Средний размер ответа"));

        Assertions.assertEquals(2, logReport.resources.get("aboba"));
        Assertions.assertEquals(1, logReport.resources.get("another_aboba_with_another_name"));

        Assertions.assertEquals(4, logReport.codes.get(200));
        Assertions.assertEquals(1, logReport.codes.get(302));
        Assertions.assertEquals(1, logReport.codes.get(404));
        Assertions.assertEquals(1, logReport.codes.get(503));

        Assertions.assertEquals(3, logReport.myStats.methods.get("GET"));
        Assertions.assertEquals(2, logReport.myStats.methods.get("PATCH"));
        Assertions.assertEquals(1, logReport.myStats.methods.get("POST"));
        Assertions.assertEquals(1, logReport.myStats.methods.get("PUT"));
        Assertions.assertEquals(1, logReport.myStats.quantitiesOfErrors.get("22.11.2023"));
        Assertions.assertEquals(1, logReport.myStats.quantitiesOfErrors.get("23.11.2023"));


    }
}
