package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class Task4 {

    public static void outputStreamComposition(String path) {
        File file = new File(path);
        try (
            OutputStream outputStream = new FileOutputStream(file);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.print("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Task4() {
    }
}
