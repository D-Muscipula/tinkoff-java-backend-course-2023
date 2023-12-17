package edu.project3.ReportGenerators;

import edu.project3.LogReport;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ReportGenerator {
    void generateReport(LogReport logReport);

    static HashMap<Integer, String> getMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Path path = Path.of("src/main/java/edu/project3/ReportGenerators/codes.txt");
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файла по заданному пути нет");
        } else {
            try {
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                lines.forEach(x -> hashMap.put(Integer.valueOf(x.split(":")[0]), x.split(":")[1]));
                return hashMap;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Некорректное значение в строке", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static LinkedHashMap<String, Integer> getSorted(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
