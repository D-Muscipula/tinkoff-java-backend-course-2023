package edu.project3;

import edu.project3.ReportGenerators.AsciiDocGenerator;
import edu.project3.ReportGenerators.MarkdownGenerator;
import edu.project3.ReportGenerators.ReportGenerator;
import edu.project3.Sources.MasterOfSources;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AppRunner {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void run(Arguments arguments) {
        MasterOfSources masterOfSources = new MasterOfSources();
        LOGGER.info("Считывание данных");
        String data = Arrays.stream(arguments.paths()).map(masterOfSources::getData).collect(Collectors.joining("\n"));
        LOGGER.info("Данные получены");

        LOGGER.info("Формирование отчёта");
        LogReport logReport = new LogReport(arguments.from(), arguments.to());
        Stream<LogRecord> logRecordStream = LogParser.getStreamOfRecords(data);
        logRecordStream.forEach(logReport::updateStatistics);
        logReport.generateStatistics();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm-ss");
        String now = "Report " + LocalDateTime.now().format(formatter);
        ReportGenerator reportGenerator;
        String path = "src/main/java/edu/project3/Reports/";
        if (arguments.dataFormat().equals("adoc")) {
            now += ".adoc";
            Path path1 = Path.of(path + now);
            reportGenerator = new AsciiDocGenerator(path1);
        } else {
            now += ".md";
            Path path1 = Path.of(path + now);
            reportGenerator = new MarkdownGenerator(path1);
        }
        reportGenerator.generateReport(logReport);
        LOGGER.info(now + " сгенерирован");
    }

    private AppRunner() {
    }
}
