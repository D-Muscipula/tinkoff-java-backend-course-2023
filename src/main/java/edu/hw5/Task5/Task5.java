package edu.hw5.Task5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {

    public static boolean isRussianCarNumber(String number) {
        String letters = "[АВЕКМНОРСТУХABEKMHOPCTYX]";
        String digits = "\\d{3}";
        Pattern pattern = Pattern.compile("^" + letters + digits + letters + "{2}" + digits + "$");
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

    private Task5() {
    }
}
