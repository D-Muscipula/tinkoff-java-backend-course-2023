package edu.project1;

public final class AppRunner {
    static public void run() {
        Session session = new Session();
        /*if (session.getWord().length() < 2) {
            return;
        }*/
        while (session.getCountOfMistakes() < Session.MAX_ATTEMPTS) { //session state
            String letter = InputReader.readMessage();
            //give up
            //GuessResult guessResult = guess(Session, letter);

        }
    }

    private AppRunner() {
    }
}
