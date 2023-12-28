package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearcher extends RecursiveTask<List<File>> {

    private final File directory;
    private final int amount;

    public DirectorySearcher(File directory, int amount) {
        this.directory = directory;
        this.amount = amount;
    }

    @Override
    protected List<File> compute() {
        int counter = 0;
        File[] files = directory.listFiles();
        List<File> directoriesContainingAmount = new ArrayList<>();
        if (files != null) {
            for (var file : files) {
                if (file.isDirectory()) {
                    DirectorySearcher task = new DirectorySearcher(file, amount);
                    task.fork();
                    directoriesContainingAmount.addAll(task.join());
                } else {
                    counter++;
                }
            }
            if (counter >= amount) {
                directoriesContainingAmount.add(directory);
            }
        }
        return directoriesContainingAmount;
    }
}
