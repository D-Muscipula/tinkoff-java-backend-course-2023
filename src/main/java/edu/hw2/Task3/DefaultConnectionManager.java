package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    final static int RANDOM_BORDER = 10;

    @Override
    public Connection getConnection() {
        Random random = new Random();
        int number = random.nextInt(RANDOM_BORDER);
        if (number <= 2) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
