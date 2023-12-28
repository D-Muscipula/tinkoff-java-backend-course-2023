package edu.hw9.Task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public final class TreeHandler {
    public static List<File> getDirectories(File startDirectory, int amountOfFiles) {
        if (startDirectory == null || startDirectory.isFile() || amountOfFiles < 0) {
            throw new IllegalArgumentException();
        }
        DirectorySearcher task = new DirectorySearcher(startDirectory, amountOfFiles);
        List<File> directories;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            directories = pool.invoke(task);
            pool.shutdown();
        }
        return directories;
    }

    public static List<File> getFiles(File startDirectory, Predicate<File> predicate) {
        if (startDirectory == null || startDirectory.isFile() || predicate == null) {
            throw new IllegalArgumentException();
        }
        PredicateSearcher task = new PredicateSearcher(startDirectory, predicate);
        List<File> files;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            files = pool.invoke(task);
            pool.shutdown();
        }
        return files;
    }

    public static Predicate<File> getExtensionPredicate(String extension) {
        return (file) -> file.getName().contains("." + extension);
    }

    public static Predicate<File> getSizePredicate(int size) {
        return (file) -> file.length() == size;
    }

    private TreeHandler() {
    }
}
