package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task6 {

    public static boolean isSubsequence(String s, String t) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var letter : s.toCharArray()) {
            stringBuilder.append(letter).append(".*");
        }
        Pattern pattern = Pattern.compile(stringBuilder.toString());
        Matcher matcher = pattern.matcher(t);
        return matcher.find();
    }

    private Task6() {
    }
}
