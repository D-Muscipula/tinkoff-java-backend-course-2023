
package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class WordOfDayParser extends DateParser {
    public WordOfDayParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> doParse(String request) {
        LocalDate localDate = LocalDate.now();
        int delta;
        switch (request) {
            case "tomorrow":
                delta = 1;
                break;

            case "today":
                delta = 0;
                break;

            case "yesterday":
                delta = -1;
                break;

            default:
                if (getNext() != null) {
                    return getNext().doParse(request);
                }
                return Optional.empty();
        }

        return Optional.of(localDate.plusDays(delta));
    }
}
