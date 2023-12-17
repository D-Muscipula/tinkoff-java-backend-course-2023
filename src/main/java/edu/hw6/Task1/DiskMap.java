package edu.hw6.Task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;

public class DiskMap extends HashMap<String, String> {

    private Path path;

    @Override public String put(String key, String value) {
        boolean isInMap = this.containsKey(key);
        String result = super.put(key, value);
        if (isInMap) { //Если есть повтор, что весь файл перезаписывается
            rewriteFile();
        } else { //Дозапись ключ:значение
            try (FileWriter writer = new FileWriter(String.valueOf(path.getFileName()), true)) {
                File file = new File(String.valueOf(path.getFileName()));

                if (!(file.length() == 0L)) {
                    writer.write("\n");
                }
                writer.write(key + ":" + value);

            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    public DiskMap() {
        Path path1 = Paths.get("default.txt");
        int i = 1;
        while (Files.exists(path1)) {
            String string = "default(" + i + ").txt";
            path1 = Paths.get(string);
            i++;
        }
        path = path1;
    }

    public DiskMap(int initialCapacity, Path path) {
        super(initialCapacity);
        readFromFile(path);
    }

    public void readFromFile(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файла по заданному пути нет");
        } else {
            try {
                List<String> lines = Files.readAllLines(path, UTF_8);
                lines.forEach(x -> super.put(x.split(":")[0], x.split(":")[1]));
                this.path = path;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Некорректное значение в строке", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String remove(Object key) {
        String result = super.remove(key);
        rewriteFile();
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        try {
            new FileWriter(String.valueOf(path), false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rewriteFile() {
        try (FileWriter writer = new FileWriter(String.valueOf(path), false)) {
            File file = new File(String.valueOf(path));
            int i = 0;
            if (!(file.length() == 0L)) {
                writer.write("\n");
            }
            for (var entry : this.entrySet()) {
                i++;
                writer.write(entry.getKey() + ":" + entry.getValue());
                if (i != this.entrySet().size()) {
                    writer.write("\n");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public DiskMap(Path path) {
        readFromFile(path);
    }

    public DiskMap(Map<? extends String, ? extends String> m, Path path) {
        super(m);
        this.path = path;
        rewriteFile();
    }
}
