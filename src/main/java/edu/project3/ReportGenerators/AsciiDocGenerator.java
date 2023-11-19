package edu.project3.ReportGenerators;

import edu.project3.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsciiDocGenerator implements ReportGenerator {
    private final Path path;

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String QUANTITY = "Количество";
    private static final String LINE_BREAK = "\n\n";

    public AsciiDocGenerator(Path path1) {
        path = path1;
    }

    @Override
    public void generateReport(LogReport logReport) {

        String table = generateTable(logReport.commonInformation, "Общая информация",
            "Метрика", "Значение"
        ) + LINE_BREAK
            + generateTable(ReportGenerator.getSorted(logReport.resources),
            "Запрашиваемые ресурсы", "Ресурс", QUANTITY
        ) + LINE_BREAK;
        HashMap<Integer, String> codes = ReportGenerator.getMap();
        table += generateTableFor3(logReport.codes, codes, "Коды ответа", "Код",
            "Имя", QUANTITY
        ) + LINE_BREAK;
        table += generateTable(ReportGenerator.getSorted(logReport.myStats.methods),
            "Методы", "Метод", QUANTITY
        ) + LINE_BREAK;
        table += generateTable(logReport.myStats.quantitiesOfErrors,
            "Количество ошибок по дням", "День", "Количество ошибок"
        );

        try {
            Files.createFile(path);
            Files.write(path, table.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Что-то пошло не так при создании файла отчета", e);
        }
        LOGGER.info(table);
    }

    public <T, M> String generateTable(Map<T, M> map, String header, String key, String value) {
        StringBuilder table = new StringBuilder();

        table.append(".").append(header).append("\n|===").append("\n");
        table.append("| ").append(key).append("| ").append(value).append("\n");

        for (var entry : map.entrySet()) {
            table.append(String.format("| %s | %s\n", entry.getKey(), entry.getValue()));
        }
        table.append("|===").append("\n");

        return table.toString();
    }

    public <T, M, K> String generateTableFor3(
        Map<T, M> map,
        Map<T, K> map2,
        String header,
        String key,
        String value1,
        String value2
    ) {
        StringBuilder table = new StringBuilder();
        table.append(".").append(header).append("\n|===\n");
        table.append("| ").append(key).append("| ").append(value1).append("| ").append(value2).append("\n");

        for (var entry : map.entrySet()) {
            table.append(String.format("| %s | %s | %s\n", entry.getKey(),
                map2.get(entry.getKey()), entry.getValue()
            ));
        }
        table.append("|===\n");

        return table.toString();
    }
}
