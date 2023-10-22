package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Session {
    private final String word;
    private String userWord;
    static final int MAX_ATTEMPTS = 5;
    private int countOfMistakes = 0;

    public Session(String word) {
        if (word.length() <= 2) {
            throw new IllegalArgumentException();
        }
        this.word = word;
        userWord = Dictionary.getStars(word.length());
    }

    public int getCountOfMistakes() {
        return countOfMistakes;
    }

    public String getUserWord() {
        return userWord;
    }

    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

    @NotNull GuessResult guess(String letter) {
        if (word.contains(letter) && !userWord.contains(letter)) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter.charAt(0)) {
                    temp.append(letter);
                } else {
                    temp.append(userWord.charAt(i));
                }
            }
            this.setUserWord(temp.toString());
            if (userWord.equals(word)) {
                return new GuessResult.Win(this);
            } else {
                return new GuessResult.SuccessfulGuess(this);
            }

        } else {
            if (!userWord.contains(letter)) {
                countOfMistakes++;
            }
            if (countOfMistakes == MAX_ATTEMPTS) {
                return new GuessResult.Defeat(this);
            } else {
                return new GuessResult.FailedGuess(this, userWord.contains(letter));
            }
        }

    }

    @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(this, true);
    }

}
