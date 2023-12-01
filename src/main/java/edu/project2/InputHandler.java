package edu.project2;

import java.util.Scanner;

public final class InputHandler {
    public static int variantHandler() {
        Scanner scanner = new Scanner(System.in);
        try {
            int number = scanner.nextInt();
            if (number == 1 || number == 2) {
                return number;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public static int xAndYHandler() {
        Scanner scanner = new Scanner(System.in);
        try {
            int number = scanner.nextInt();
            if (number > -1) {
                return number;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public static int sideHandler() {
        Scanner scanner = new Scanner(System.in);
        try {
            int number = scanner.nextInt();
            if (number > 0) {
                return number;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private InputHandler() {
    }
}
