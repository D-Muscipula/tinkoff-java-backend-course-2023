package edu.hw3.Task4;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public final class Task4 {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static String convertToRoman(int number) {
        TreeMap<Integer, String> numbers = new TreeMap<>(Collections.reverseOrder());
        int tempNumber = number;
        numbers.put(1000, "M");
        numbers.put(900, "CM");
        numbers.put(500, "D");
        numbers.put(400, "CD");
        numbers.put(100, "C");
        numbers.put(90, "XC");
        numbers.put(50, "L");
        numbers.put(40, "XL");
        numbers.put(10, "X");
        numbers.put(9, "IX");
        numbers.put(5, "V");
        numbers.put(4, "IV");
        numbers.put(1, "I");
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, String> str : numbers.entrySet()) {
            int countOfOccurrences = tempNumber / str.getKey();
            tempNumber -= countOfOccurrences * str.getKey();
            stringBuilder.append(str.getValue().repeat(countOfOccurrences));
        }
        String result = stringBuilder.toString();
        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    private Task4() {
    }
}

