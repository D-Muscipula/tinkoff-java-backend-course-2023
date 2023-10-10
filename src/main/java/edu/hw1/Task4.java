package edu.hw1;

public final class Task4 {

    private Task4() {

    }

    public static String fixString(String string) {
        if (string == null) {
            return null;
        }
        String tempString = string;
        char[] chars = tempString.toCharArray();
        for (int i = 0; i < string.length() - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        tempString = new String(chars);
        return tempString;
    }
}
