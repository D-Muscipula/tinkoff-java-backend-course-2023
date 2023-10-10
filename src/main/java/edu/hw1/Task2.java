package edu.hw1;

public final class Task2 {

    private Task2() {

    }

    @SuppressWarnings("checkstyle:MagicNumber") public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }
        int count = 0;
        int num = number;
        while (num != 0) {
            num = num / 10;
            count++;
        }
        return count;
    }
}
