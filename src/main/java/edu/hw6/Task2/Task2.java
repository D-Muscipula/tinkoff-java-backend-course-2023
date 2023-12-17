package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class Task2 {

    public static void cloneFile(Path path) {
        if (!Files.exists(path)) {
            throw new RuntimeException(new NoSuchFileException(String.valueOf(path)));
        } else {
            String fileName = String.valueOf(path);
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex != -1) {
                String[] a = {fileName.substring(0, dotIndex), fileName.substring(dotIndex)};
                Path tempPath = Path.of(a[0] + " - копия" + a[1]);
                int i = 2;
                while (isUnsuccessfulAttemptToCopyFile(path, tempPath)) {
                    tempPath = Path.of(a[0] + " - копия " + "(" + i + ")" + a[1]);
                    i++;
                }
            } else {
                Path tempPath = Path.of(fileName + " - " + "копия");
                int i = 2;
                while (isUnsuccessfulAttemptToCopyFile(path, tempPath)) {
                    tempPath = Path.of(fileName + " - копия (" + i + ")");
                    i++;
                }
            }
        }
    }

    public static boolean isUnsuccessfulAttemptToCopyFile(Path original, Path copy) {
        if (Files.exists(original) && !Files.exists(copy)) {
            try {
                Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        return true;
    }

    private Task2() {
    }
}
