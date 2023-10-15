package edu.project1;

import java.util.Random;

public final class Dictionary {
    private static final String[] WORDS = {
        "кот",
        "дом",
        "солнце",
        "река",
        "цветок",
        "море",
        "дерево",
        "книга",
        "яблоко",
        "шар"
    };

    private Dictionary() {
    }

    public static String getWord() {
        Random random = new Random();
        int rand = random.nextInt(WORDS.length);
        return WORDS[rand];
    }

    public static String getStars(int n) {
        return "*".repeat(n);
    }
}
