package edu.project3;

import edu.project3.Sources.MasterOfSources;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class LogReport {
    private static final String QUANTITY = "Количество запросов";
    private static final String FILES = "Файл(-ы)";

    private static final String START_DATE = "Начальная дата";

    private static final String END_DATE = "Конечная дата";

    private static final String AVERAGE = "Средний размер ответа";

    private LocalDateTime from;

    private LocalDateTime to;

    public LogReport(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        if (from == null) {
            this.from = LocalDateTime.MIN;
        }
        this.to = to;
        if (to == null) {
            this.to = LocalDateTime.MAX;
        }
    }

    private BigInteger bytes = BigInteger.valueOf(0);
    public final LinkedHashMap<String, String> commonInformation = new LinkedHashMap<>() {{
        put(FILES, "");
        put(START_DATE, "-");
        put(END_DATE, "-");
        put(QUANTITY, "0");
        put(AVERAGE, "0b");
    }};
    public final TreeMap<String, Integer> resources = new TreeMap<>();
    public final TreeMap<Integer, Integer> codes = new TreeMap<>();

    public final MyStats myStats = new MyStats();

    public void generateStatistics() {
        commonInformation.put(FILES, String.join(" ", MasterOfSources.paths));
        BigInteger quantity = BigInteger.valueOf(Integer.parseInt(commonInformation.get(QUANTITY)));
        commonInformation.put(AVERAGE, bytes.divide(quantity) + "b");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        if (!from.isEqual(LocalDateTime.MIN)) {
            String result = from.format(formatter);
            if (from.getMinute() == 0 && from.getHour() == 0) {
                result = result.split(" ")[0];
            }
            commonInformation.put(START_DATE, result);
        }

        if (!to.isEqual(LocalDateTime.MAX)) {
            String result = to.format(formatter);
            if (to.getMinute() == 0 && to.getHour() == 0) {
                result = result.split(" ")[0];
            }
            commonInformation.put(END_DATE, result);
        }
    }

    public void updateStatistics(LogRecord logRecord) {
        LocalDateTime recordTime = logRecord.timeLocal().toLocalDateTime();
        if ((recordTime.isAfter(from) || recordTime.isEqual(from))
            && (recordTime.isBefore(to) || recordTime.isEqual(to))) {
            countRequests(logRecord);
            countBytes(logRecord);
            askedSources(logRecord);
            getQuantityOfCodes(logRecord);
            myStats.getMethods(logRecord);
            myStats.getErrors(logRecord);
        }
    }

    public void countRequests(LogRecord logRecord) {
        if (!logRecord.requestMethod().equals("-")) {
            commonInformation.put(QUANTITY, String.valueOf(Integer.parseInt(commonInformation.get(QUANTITY))
                + 1));
        }
    }

    public void countBytes(LogRecord logRecord) {
        bytes = bytes.add(BigInteger.valueOf(logRecord.bodyBytesSent()));
    }

    public void askedSources(LogRecord logRecord) {
        String resource = logRecord.httpReferer();
        if (!resource.equals("-") && !resources.containsKey(resource)) {
            resources.put(resource, 1);
        } else if (resources.containsKey(resource)) {
            resources.put(resource, resources.get(resource) + 1);
        }
    }

    public void getQuantityOfCodes(LogRecord logRecord) {
        int code = logRecord.status();
        if (!codes.containsKey(code)) {
            codes.put(code, 1);
        } else {
            codes.put(code, codes.get(code) + 1);
        }
    }
}
