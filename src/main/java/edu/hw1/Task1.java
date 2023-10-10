package edu.hw1;

import java.util.regex.Pattern;

public final class Task1 {

    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {

    }

    public static int minutesToSeconds(String time) {
        String[] minutesAndSeconds = time.split(":");
        //String regex = "\\d+";
        //LOGGER.info(Arrays.toString(minutesAndSeconds));
        String regex = "\\d+";
        if (minutesAndSeconds.length != 2 || minutesAndSeconds[1].length() != 2) {
            return -1;
        } else if (!Pattern.matches(regex, minutesAndSeconds[0]) || !Pattern.matches(regex, minutesAndSeconds[1])) {
            return -1;
        }
        int minutes = Integer.parseInt(minutesAndSeconds[0]);
        int seconds = Integer.parseInt(minutesAndSeconds[1]);
        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
