package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class LogParser {
    @SuppressWarnings("checkstyle:MagicNumber") public static LogRecord parseLog(String log) {
        String regex = "^(\\S+) - (\\S+) \\[([^]]+)] \"(\\S+) (\\S+) \\S+\" (\\d+) (\\d+) \"(\\S+)\" \"([^\"]+)\"";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log);
        if (matcher.matches()) {
            String remoteAddr = matcher.group(1);
            String remoteUser = matcher.group(2);
            String timeLocal = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
            OffsetDateTime timeLocalOffsetDateTime = OffsetDateTime.parse(timeLocal, formatter);
            String requestMethod = matcher.group(4);
            String requestUri = matcher.group(5);
            String status = matcher.group(6);
            String bodyBytesSent = matcher.group(7);
            String httpReferer = matcher.group(8);
            String httpUserAgent = matcher.group(9);
            return new LogRecord(remoteAddr, remoteUser, timeLocalOffsetDateTime,
                requestMethod, requestUri, Integer.parseInt(status),
                Integer.parseInt(bodyBytesSent), httpReferer, httpUserAgent
            );
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Stream<LogRecord> getStreamOfRecords(String data) {
        return Arrays.stream(data.split("\n")).map(LogParser::parseLog);
    }

    private LogParser() {
    }
}
