package edu.project1;

import java.util.Scanner;
import static edu.project1.OutputPrinter.incorrectInput;

public final class InputReader {

    public static String readAndValidateMessage() {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();
        message = message.toLowerCase();
        while (!(message.length() == 1 && (message.charAt(0) >= 'а' && message.charAt(0) <= 'я'))) {
            if (message.equals("сдаться")) {
                return message;
            }
            incorrectInput();
            message = scanner.next();
            message = message.toLowerCase();
        }
        return message;
    }

    private InputReader() {
    }
}
