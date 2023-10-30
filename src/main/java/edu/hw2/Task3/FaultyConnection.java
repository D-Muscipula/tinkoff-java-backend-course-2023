package edu.hw2.Task3;

import java.util.Random;

public class FaultyConnection implements Connection {

    final static int RANDOM_BORDER = 10;

    @Override
    public void execute(String command) {
        Random random = new Random();
        int rand = random.nextInt(RANDOM_BORDER);
        if (rand < (RANDOM_BORDER / 2)) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
