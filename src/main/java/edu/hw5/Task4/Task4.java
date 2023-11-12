package edu.hw5.Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task4 {

    public static boolean isContainingSymbol(String password) {
        Pattern pattern = Pattern.compile("^.*[~!@#$%^&*|]+.*$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private Task4() {
    }
}
