package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryTest {
    private final String[] words = {
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
    @Test
    @DisplayName("Метод getWord возвращает случайное слово из массива")
    void getWordTest() {
        boolean firstPart = false;
        boolean secondPart = false;
        boolean thirdPart = false;
        for (int i = 0; i < 10000; i++) {
            String temp = Dictionary.getWord();
            if (temp.equals(words[0]) || temp.equals(words[1]) || temp.equals(words[2])) {
                firstPart = true;
            } else if (temp.equals(words[3]) || temp.equals(words[4]) || temp.equals(words[5]) ||
                temp.equals(words[6])) {
                secondPart = true;
            } else if (temp.equals(words[7]) || temp.equals(words[8]) || temp.equals(words[9])) {
                thirdPart = true;
            }
            if (firstPart && secondPart && thirdPart) {
                break;
            }
        }
        Assertions.assertTrue(firstPart && secondPart && thirdPart);
    }

    @Test
    @DisplayName("Метод getStars возвращает последовательность *, равную длине слова")
    void getStarsTest() {
        for (String word:
             words) {
            String stars = Dictionary.getStars(word.length());
            Assertions.assertEquals("*".repeat(word.length()), stars);
        }
    }
}
