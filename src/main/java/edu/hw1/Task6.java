package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public final class Task6 {
    private static final int KAPREKAR_NUMBER = 6174;
    private static final int THOUSAND = 1000;
    private static final int TEN = 10;

    private Task6() {

    }

    public static int countK(int n) {
        if (n == KAPREKAR_NUMBER) {
            return 0;
        } else if (Task2.countDigits(n) != Task2.countDigits(THOUSAND) || n == THOUSAND) {
            return -1;
        }
        String number = String.valueOf(n);
        char[] digits = number.toCharArray();
        Character[] digitsCharacter = new Character[digits.length];
        for (int i = 0; i < digits.length; i++) {
            digitsCharacter[i] = digits[i];
        }
        Arrays.sort(digitsCharacter, Collections.reverseOrder());
        for (int i = 0; i < digits.length; i++) {
            digits[i] = digitsCharacter[i];
        }
        Integer num1 = Integer.parseInt(new String(digits));
        Arrays.sort(digits);
        Integer num2 = Integer.parseInt(new String(digits));
        if (num1.equals(num2)) {
            return -1;
        } else {
            int temp = num1 - num2;
            int countOfDigits = Task2.countDigits(temp);
            if (countOfDigits < Task2.countDigits(num1)) {
                temp *= (int) Math.pow(TEN, Task2.countDigits(num1) - countOfDigits);
            }
            int k = countK(temp);
            k = k == -1 ? -1 : k + 1;
            return k;
        }
    }
}
