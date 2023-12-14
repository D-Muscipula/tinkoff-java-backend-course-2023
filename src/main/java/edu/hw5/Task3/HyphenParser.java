package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class HyphenParser extends DateParser {
    public HyphenParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> doParse(String request) {
        String format1 = "\\d{4}-\\d{2}-\\d{2}";
        String format2 = "\\d{4}-\\d{2}-\\d";
        if (request.matches(format1)) {
            String dateFormat = "yyyy'-'MM'-'dd";
            LocalDate localDate = LocalDate.parse(request, DateTimeFormatter.ofPattern(dateFormat));
            return Optional.of(localDate);
        } else if (request.matches(format2)) {
            String dateFormat = "yyyy'-'MM'-'d";
            LocalDate localDate = LocalDate.parse(request, DateTimeFormatter.ofPattern(dateFormat));
            return Optional.of(localDate);
        } else if (getNext() != null) {
            return getNext().doParse(request);
        }
        return Optional.empty();
    }
}
