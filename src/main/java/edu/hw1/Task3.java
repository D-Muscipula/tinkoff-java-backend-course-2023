package edu.hw1;

import java.util.Arrays;

public final class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] mainArray, int[] arrayForInsert) {
        if (mainArray == null || arrayForInsert == null || mainArray.length < 1 || arrayForInsert.length < 1) {
            return false;
        }
        int minOfMainArray = Arrays.stream(mainArray).min().getAsInt();
        int minOfArrayForInsert = Arrays.stream(arrayForInsert).min().getAsInt();
        int maxOfMainArray = Arrays.stream(mainArray).max().getAsInt();
        int maxOfArrayForInsert = Arrays.stream(arrayForInsert).max().getAsInt();
        return minOfMainArray > minOfArrayForInsert && maxOfMainArray < maxOfArrayForInsert;
    }
}
