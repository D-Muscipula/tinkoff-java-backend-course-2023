package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class DaysAgoParser extends DateParser {
    public DaysAgoParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> doParse(String request) {
        String regex = "(\\d+) days ago";
        Pattern pattern = Pattern.compile(regex);
        var match = pattern.matcher(request);
        if (request.equals("1 day ago")) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else if (match.find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(match.group(1))));
        }
        return Optional.empty();
    }
}
