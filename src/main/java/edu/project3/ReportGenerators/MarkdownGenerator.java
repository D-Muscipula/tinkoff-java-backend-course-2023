package edu.project3.ReportGenerators;

import edu.project3.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MarkdownGenerator implements ReportGenerator {
    private static final String QUANTITY = "Количество";
    private static final String DOUBLE_LINE_BREAK = "\n\n";
    private final Path path;
    private final static Logger LOGGER = LogManager.getLogger();

    public MarkdownGenerator(Path path1) {
        path = path1;
    }

    @Override
    public void generateReport(LogReport logReport) {

        String markdownTableS = generateTable(logReport.commonInformation, "Общая информация",
            "Метрика", "Значение"
        ) + DOUBLE_LINE_BREAK
            + generateTable(ReportGenerator.getSorted(logReport.resources),
            "Запрашиваемые ресурсы", "Ресурс", QUANTITY
        ) + DOUBLE_LINE_BREAK;
        HashMap<Integer, String> codes = ReportGenerator.getMap();
        markdownTableS += generateTableFor3(logReport.codes, codes, "Коды ответа", "Код",
            "Имя", QUANTITY
        ) + DOUBLE_LINE_BREAK;
        markdownTableS += generateTable(ReportGenerator.getSorted(logReport.myStats.methods),
            "Методы", "Метод", QUANTITY
        ) + DOUBLE_LINE_BREAK;
        markdownTableS += generateTable(logReport.myStats.quantitiesOfErrors,
            "Количество ошибок по дням", "День", "Количество ошибок"
        );

        try {
            Files.createFile(path);
            Files.write(path, markdownTableS.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("что-то пошло не так при создании файла отчета", e);
        }
        LOGGER.info(markdownTableS);

    }

    public <T, M> String generateTable(Map<T, M> map, String header, String key, String value) {
        StringBuilder markdownTable = new StringBuilder();

        markdownTable.append("####").append(" ").append(header).append("\n");
        markdownTable.append("| ").append(key).append("| ").append(value).append("  |\n");
        markdownTable.append("| ---- | ----- |\n");

        for (var entry : map.entrySet()) {
            markdownTable.append(String.format("| %s | %s |\n", entry.getKey(), entry.getValue()));
        }

        return markdownTable.toString();
    }

    public <T, M, K> String generateTableFor3(
        Map<T, M> map,
        Map<T, K> map2,
        String header,
        String key,
        String value1,
        String value2
    ) {
        StringBuilder markdownTable = new StringBuilder();

        markdownTable.append("#### ").append(header).append("\n");
        markdownTable.append("| ").append(key).append("| ").append(value1).append("| ").append(value2).append(" |\n");
        markdownTable.append("| --- | ----- | ----- |\n");

        for (var entry : map.entrySet()) {
            markdownTable.append(String.format("| %s | %s | %s |\n", entry.getKey(),
                map2.get(entry.getKey()), entry.getValue()
            ));
        }

        return markdownTable.toString();
    }
}
