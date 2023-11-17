package edu.hw3.Task2;

import java.util.ArrayList;

public final class Task2 {
    public static String[] clusterize(String str) {
        int brackets = 0;
        if (str == null || str.length() % 2 == 1) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> clusters = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (Character symbol
            : str.toCharArray()) {
            temp.append(symbol);
            if (symbol == '(') {
                brackets++;
            } else if (symbol == ')') {
                brackets--;
                if (brackets == 0) {
                    clusters.add(temp.toString());
                    temp = new StringBuilder();
                }
                if (brackets < 0) {
                    throw new IllegalArgumentException();
                }

            } else {
                throw new IllegalArgumentException();
            }
        }
        return clusters.toArray(new String[0]);
    }

    private Task2() {
    }
}
