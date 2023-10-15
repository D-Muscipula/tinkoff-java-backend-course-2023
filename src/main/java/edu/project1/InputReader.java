package edu.project1;

import java.util.Scanner;

public final class InputReader {
    public static String readMessage() {
        System.out.println("> Quess a letter");
        String pattern = "\\[a-z]";
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (!message.matches(pattern)) {
            message = scanner.next();
            if (message.equals("give up")) {
                return message;
            }
        }
        return message;
    }

    private InputReader() {
    }
}
