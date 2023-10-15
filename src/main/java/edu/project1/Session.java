package edu.project1;

public class Session {
    private String word = "";
    private String userWord = "";
    static final int MAX_ATTEMPTS = 5;
    private int countOfMistakes = 0;

    public Session() {
        word = Dictionary.getWord();
        userWord = Dictionary.getStars(word.length());
    }

    public int getCountOfMistakes() {
        return countOfMistakes;
    }

    public String getUserWord() {
        return userWord;
    }

    public Session setUserWord(String userWord) {
        this.userWord = userWord;
        return this;
    }

    public void setCountOfMistakes(int countOfMistakes) {
        this.countOfMistakes = countOfMistakes;
    }

    public String getWord() {
        return word;
    }

}
