package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SlashParser extends DateParser {
    public SlashParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> doParse(String request) {
        String format1 = "\\d{1,2}/\\d{1,2}/\\d{4}";
        String format2 = "\\d{1,2}/\\d{1,2}/\\d{2}";
        if (request.matches(format1)) {
            String dateFormat = "M'/'d'/'yyyy";
            LocalDate localDate = LocalDate.parse(request, DateTimeFormatter.ofPattern(dateFormat));
            return Optional.of(localDate);
        } else if (request.matches(format2)) {
            String dateFormat = "M'/'d'/'yy";
            LocalDate localDate = LocalDate.parse(request, DateTimeFormatter.ofPattern(dateFormat));
            return Optional.of(localDate);
        } else if (getNext() != null) {
            return getNext().doParse(request);
        }
        return Optional.empty();
    }
}
