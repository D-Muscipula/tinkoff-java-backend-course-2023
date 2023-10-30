package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {
    @Test
    @DisplayName("Если слово меньше 3 символов, то игра не начинается")
    void illegalArgumentTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Session("да"));
        Assertions.assertDoesNotThrow(() -> {
            new Session("шар");
        });

    }

    @Test
    @DisplayName("При угаданной букве меняется userWord и возвращается SuccessfulGuess, при победе - Win, " +
    "при этом счетчик ошибок остается прежним")
    void guessResultWinTest() {
        Session session = new Session("шар");
        int countOfMistakes = session.getCountOfMistakes();
        GuessResult guessResult = session.guess("а");
        Assertions.assertEquals(countOfMistakes, session.getCountOfMistakes());
        Assertions.assertEquals("*а*", guessResult.state());
        Assertions.assertInstanceOf(GuessResult.SuccessfulGuess.class, guessResult);
        guessResult = session.guess("г");
        Assertions.assertEquals("*а*", guessResult.state());
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, guessResult);
        guessResult = session.guess("ш");
        Assertions.assertInstanceOf(GuessResult.SuccessfulGuess.class, guessResult);
        Assertions.assertEquals("ша*", guessResult.state());
        guessResult = session.guess("р");
        Assertions.assertInstanceOf(GuessResult.Win.class, guessResult);
        Assertions.assertEquals("шар", guessResult.state());
    }

    @Test
    @DisplayName("При вводе отсутствующей буквы поле CountOfMistakes увеличивается на 1 и " +
        "возвращается FailedGuess, при поражении - Defeat")
    void guessResultDefeatTest() {
        Session session = new Session("шар");
        int countOfMistakes = session.getCountOfMistakes();
        GuessResult guessResult = session.guess("в");
        Assertions.assertEquals(countOfMistakes + 1, session.getCountOfMistakes());
        Assertions.assertEquals("***", guessResult.state());
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, guessResult);
        guessResult = session.guess("г");
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, guessResult);
        guessResult = session.guess("д");
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, guessResult);
        guessResult = session.guess("л");
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, guessResult);
        guessResult = session.guess("а");
        Assertions.assertInstanceOf(GuessResult.SuccessfulGuess.class, guessResult);
        guessResult = session.guess("и");
        Assertions.assertInstanceOf(GuessResult.Defeat.class, guessResult);
    }

    @Test
    @DisplayName(
        "Если вводится буква, которая уже была угадана, то возвращается FailedGuess, но счетчик ошибок не увеличивается")
    void guessResultRepetitionOfGuessedLetterTest() {
        Session session = new Session("шар");
        GuessResult guessResult = session.guess("а");
        Assertions.assertInstanceOf(GuessResult.SuccessfulGuess.class, guessResult);
        int countOfMistakesBefore = session.getCountOfMistakes();
        guessResult = session.guess("а");
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, guessResult);
        Assertions.assertEquals("Эту букву вы уже угадали", guessResult.message());
        Assertions.assertEquals(countOfMistakesBefore, session.getCountOfMistakes());
    }

    @Test
    @DisplayName("При вызове giveUp возвращается Defeat с полем isGivenUp равным true")
    void giveUpTest() {
        Session session = new Session("шар");
        GuessResult guessResult = session.giveUp();
        Assertions.assertInstanceOf(GuessResult.Defeat.class, guessResult);
        GuessResult.Defeat guessResult1 = (GuessResult.Defeat) guessResult;
        Assertions.assertTrue(guessResult1.isGivenUp());
    }
}
