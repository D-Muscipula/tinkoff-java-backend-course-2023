package edu.hw6.Task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter anotherOneFilter) {
        AbstractFilter temp = this;
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return temp.accept(entry) && anotherOneFilter.accept(entry);
            }
        };
    }

    static AbstractFilter largerThan(int i) {
        return (entry) -> Files.size(entry) > i;
    }

    static AbstractFilter regexContains(String s) {
        return entry -> Pattern.compile(s).matcher(entry.toString()).find();
    }

    static AbstractFilter globMatches(String s) {

        return entry -> {
            try {
                return FileSystems.getDefault().getPathMatcher("glob:" + s).matches(entry.getFileName());
            } catch (Exception e) {
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... magicNumbers) {
        return entry -> {
            try (FileInputStream fileInputStream = new FileInputStream(String.valueOf(entry))) {
                for (int magicNumber: magicNumbers) {
                    if (fileInputStream.read() != magicNumber) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        };
    }
}
