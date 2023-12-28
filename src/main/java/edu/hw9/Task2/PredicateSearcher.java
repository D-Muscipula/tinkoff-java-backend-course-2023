package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class PredicateSearcher extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    public PredicateSearcher(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();
        List<File> neededFiles = new ArrayList<>();
        if (files != null) {
            for (var file : files) {
                if (file.isDirectory()) {
                    PredicateSearcher task = new PredicateSearcher(file, predicate);
                    task.fork();
                    neededFiles.addAll(task.join());
                } else {
                    if (predicate.test(file)) {
                        neededFiles.add(file);
                    }
                }
            }
        }
        return neededFiles;
    }
}
