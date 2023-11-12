package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public final class Task3 {
    public static Optional<LocalDate> parseDate(String string) {
        DaysAgoParser daysAgoParser = new DaysAgoParser(null);
        WordOfDayParser wordOfDayParser = new WordOfDayParser(daysAgoParser);
        SlashParser slashParser = new SlashParser(wordOfDayParser);
        HyphenParser hyphenParser = new HyphenParser(slashParser);
        try {
            return hyphenParser.doParse(string);
        } catch (Exception e) {
            return Optional.empty();
        }

        //return Optional.empty();
    }

    private Task3() {

    }
}
