package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogParserTest {
    @Test
    void parseLogTest() {
        String logLine = "93.180.71.3 - aboba [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"http_referer\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        OffsetDateTime timeLocalOffsetDateTime = OffsetDateTime.parse("17/May/2015:08:05:32 +0000", formatter);
        LogRecord logRecord = new LogRecord("93.180.71.3", "aboba", timeLocalOffsetDateTime, "GET", "/downloads/product_1", 304, 0, "http_referer", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)");
        Assertions.assertEquals(logRecord, LogParser.parseLog(logLine));
    }
}
