package edu.project1;

public final class AppRunner {

    static public void run() {
        GuessResult guessResult = null;
        Session session;
        try {
            session = new Session(Dictionary.getWord());
        } catch (Exception e) {
            OutputPrinter.incorrectWord();
            return;
        }

        while (!(guessResult instanceof GuessResult.Defeat || guessResult instanceof GuessResult.Win)) {
            OutputPrinter.guessALetter();
            String letter = InputReader.readAndValidateMessage();
            guessResult = tryGuess(session, letter);
            OutputPrinter.printMessageAndState(guessResult);

        }
    }

    private AppRunner() {
    }

    private static GuessResult tryGuess(Session session, String input) {
        if (input.equals("сдаться")) {
            return session.giveUp();
        }
        return session.guess(input);
    }
}
