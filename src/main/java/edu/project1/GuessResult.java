package edu.project1;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {

    String HIT = "Угадали!";
    String MISS = "Не угадали, %d ошибки из %d";
    String MISSONE = "Не угадали, %d ошибка из %d";
    String MISSFIVE = "Не угадали, %d ошибок из %d";

    String state();

    @NotNull String message();

    record Defeat(Session session, boolean isGivenUp) implements GuessResult {
        Defeat(Session session) {
            this(session, false);
        }

        @Override
        public String state() {
            return session.getUserWord();
        }

        @Override
        public @NotNull String message() {
            String miss = MISS;
            if (session.getCountOfMistakes() == 1) {
                miss = MISSONE;
            } else if (session.getCountOfMistakes() == Session.MAX_ATTEMPTS) {
                miss = MISSFIVE;
            }
            return isGivenUp ? "Жаль..." : miss.formatted(session.getCountOfMistakes(), Session.MAX_ATTEMPTS);
        }
    }

    record Win(Session session) implements GuessResult {
        @Override
        public String state() {
            return session.getUserWord();

        }

        @Override
        public @NotNull String message() {
            return HIT;
        }
    }

    record SuccessfulGuess(Session session) implements GuessResult {
        @Override
        public String state() {
            return session.getUserWord();
        }

        @Override
        public @NotNull String message() {
            return HIT;
        }
    }

    record FailedGuess(Session session, boolean wasBefore) implements GuessResult {

        @Override
        public String state() {
            return session.getUserWord();
        }

        @Override
        public @NotNull String message() {
            String miss = MISS;
            if (session.getCountOfMistakes() == 1) {
                miss = MISSONE;
            } else if (session.getCountOfMistakes() == Session.MAX_ATTEMPTS) {
                miss = MISSFIVE;
            }
            return wasBefore ? "Эту букву вы уже угадали" : miss
                .formatted(session.getCountOfMistakes(), Session.MAX_ATTEMPTS);
        }
    }
}
