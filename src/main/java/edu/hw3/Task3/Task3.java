package edu.hw3.Task3;

import java.util.HashMap;

public final class Task3 {
    public static <T> HashMap<T, Integer> freqDict(T[] words) {
        if (words == null) {
            throw new IllegalArgumentException();
        }
        HashMap<T, Integer> hashMap = new HashMap<>();
        try {
            for (T word
                : words) {
                if (!hashMap.containsKey(word)) {
                    hashMap.put(word, 1);
                } else {
                    hashMap.put(word, hashMap.get(word) + 1);
                }

            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return hashMap;

    }

    private Task3() {
    }
}
