package edu.project3.Sources;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GlobSource implements LogSource {
    @Override
    public String getData(String path) {
        try {
            return readFilesMatchingGlob(path);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в glob строке " + path, e);
        }
    }

    private String readFilesMatchingGlob(String path) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
        try (Stream<Path> matchedFiles = Files.walk(Paths.get(".").toAbsolutePath().normalize())
            .filter(pathMatcher::matches)) {
            List<Path> paths = matchedFiles.toList();
            FileSource fileSource = new FileSource();
            ArrayList<String> strings = new ArrayList<>();
            for (Path file : paths) {
                strings.add(fileSource.getData(String.valueOf(file)));
            }
            return String.join("\n", strings);
        }
    }
}
