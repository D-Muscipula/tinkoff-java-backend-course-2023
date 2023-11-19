package edu.project3.Sources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileSource implements LogSource {
    public static final ArrayList<String> PATHS = new ArrayList<>();

    @Override
    public String getData(String path) {
        try {
            Path pathOfFile = Paths.get(path);
            String fileName = pathOfFile.getFileName().toString();
            PATHS.add(fileName);
            return new String(Files.readAllBytes(pathOfFile)).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Что-то не так с файлом");
        }
    }
}
