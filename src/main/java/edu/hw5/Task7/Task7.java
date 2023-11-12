package edu.hw5.Task7;

import java.util.regex.Pattern;

public final class Task7 {
    public static boolean isTheThirdZero(String s) {
        String regexp = "^[01]{2}0[01]*$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isTheSameSymbol(String s) {
        String regexp = "^0[01]*0$" + "|^1[01]*1$" + "|^0$|^1$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isBetweenOneAndThree(String s) {
        String regexp = "^[01]{1,3}$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    private Task7() {
    }
}
