package edu.hw3.Task1;

public final class Task1 {
    public static String atbash(String string) {
        if (string == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : string.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                stringBuilder.append((char) ((int) 'z' - (int) c + (int) 'a'));

            } else if ('A' <= c && c <= 'Z') {
                stringBuilder.append((char) ((int) 'Z' - (int) c + (int) 'A'));
            } else {
                stringBuilder.append(c);
            }

        }
        return stringBuilder.toString();
    }

    private Task1() {
    }
}
