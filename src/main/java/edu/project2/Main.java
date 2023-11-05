package edu.project2;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        try {
            AppRunner.run();
        } catch (Exception e) {
            MessagePrinter.incorrectInput();
        }
    }
}
