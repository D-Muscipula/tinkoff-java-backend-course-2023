package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public record Arguments(String[] paths, LocalDateTime from, LocalDateTime to, String dataFormat) {
    public static Arguments getCommandFromString(String stringOfArgunents) {
        String[] arguments = stringOfArgunents.split(" --");
        arguments[0] = arguments[0].replaceFirst("--", "");
        ArrayList<String> paths = new ArrayList<>();
        LocalDateTime from = null;
        LocalDateTime to = null;
        String format = null;
        for (String argument : arguments) {
            String[] typeAndArgument = argument.split(" ");
            switch (typeAndArgument[0]) {
                case "path":
                    paths.addAll(Arrays.asList(typeAndArgument).subList(1, typeAndArgument.length));
                    break;
                case "from":
                    try {
                        from = LocalDateTime.parse(typeAndArgument[1]);
                    } catch (Exception e) {
                        LocalDate temp = LocalDate.parse(typeAndArgument[1]);
                        from = temp.atTime(0, 0, 0);
                    }
                    break;
                case "to":
                    try {
                        to = LocalDateTime.parse(typeAndArgument[1]);
                    } catch (Exception e) {
                        LocalDate temp = LocalDate.parse(typeAndArgument[1]);
                        to = temp.atTime(0, 0, 0);
                    }
                    break;
                case "format":
                    format = typeAndArgument[1];
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + typeAndArgument[0]);
            }
        }
        return new Arguments(paths.toArray(new String[0]), from, to, format);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Arguments arguments = (Arguments) o;
        return Arrays.equals(paths, arguments.paths) && Objects.equals(from, arguments.from)
            && Objects.equals(to, arguments.to) && Objects.equals(dataFormat, arguments.dataFormat);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(from, to, dataFormat);
        result = 31 * result + Arrays.hashCode(paths);
        return result;
    }
}
