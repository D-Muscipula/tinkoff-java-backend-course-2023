package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Task1 {
    public static final String FORMAT = "yyyy'-'MM'-'dd','HH':'mm";
    public static final int LENGTH_OF_FIRST_DATE = 16;

    public static String getAverageTime(String sessions) {
        String[] arrayOfSessions = sessions.split("\n");
        Duration time = Duration.ofSeconds(0);
        for (var session : arrayOfSessions) {
            String tempSession = session.replace(" ", "");
            try {
                LocalDateTime curDateTimeStart =
                    LocalDateTime.parse(
                        tempSession.substring(0, LENGTH_OF_FIRST_DATE),
                        DateTimeFormatter.ofPattern(FORMAT)
                    );
                LocalDateTime curDateTimeEnd =
                    LocalDateTime.parse(
                        tempSession.substring(LENGTH_OF_FIRST_DATE + 1),
                        DateTimeFormatter.ofPattern(FORMAT)
                    );
                Duration duration = Duration.between(curDateTimeStart, curDateTimeEnd);
                if (duration.isNegative()) {
                    throw new IllegalArgumentException("Time goes back 0_0");
                }
                time = time.plus(duration);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }

        }
        time = time.dividedBy(arrayOfSessions.length);
        return time.toHours() + "ч " + time.toMinutesPart() + "м";
    }

    private Task1() {

    }
}
