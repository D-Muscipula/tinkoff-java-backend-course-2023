package edu.hw2;

import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.StableConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    final static int BIG_NUMBER_FOR_BIG_CHANCE = 10000;

    @Test
    void StableConnectionExecuteWillNeverThrowConnectionException() {
        boolean isException = false;
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            try (StableConnection stableConnection = new StableConnection()) {
                stableConnection.execute("");
            } catch (Exception e) {
                isException = true;
                break;
            }
        }
        Assertions.assertFalse(isException);
    }

    @Test
    void faultyConnectionExecuteCanThrowConnectionException() {
        boolean isException = false;
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            try (FaultyConnection faultyConnection = new FaultyConnection()) {
                faultyConnection.execute("");
            } catch (Exception e) {
                isException = true;
                break;
            }
        }
        Assertions.assertTrue(isException);
    }

    @Test
    void faultyConnectionExecuteMayNotThrowConnectionException() {
        boolean isException = true;
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            try (FaultyConnection faultyConnection = new FaultyConnection()) {
                faultyConnection.execute("");
                isException = false;
                break;
            } catch (Exception ignored) {

            }
        }
        Assertions.assertFalse(isException);
    }

    @Test
    void DefaultConnectionManagerCanReturnFaultyConnection() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        boolean isFaultyConnection = false;
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            if (defaultConnectionManager.getConnection() instanceof FaultyConnection) {
                isFaultyConnection = true;
                break;
            }
        }
        Assertions.assertTrue(isFaultyConnection);
    }

    @Test
    void DefaultConnectionManagerCanReturnStableConnection() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        boolean isStableConnection = false;
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            if (defaultConnectionManager.getConnection() instanceof StableConnection) {
                isStableConnection = true;
                break;
            }
        }
        Assertions.assertTrue(isStableConnection);
    }

    @Test
    void FaultyConnectionManagerAlwaysReturnsFaultyConnection() {
        FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        boolean isStableConnection = false;
        boolean isFaultyConnection = false;
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            if (faultyConnectionManager.getConnection() instanceof StableConnection) {
                isStableConnection = true;
                break;
            } else if (faultyConnectionManager.getConnection() instanceof FaultyConnection) {
                isFaultyConnection = true;
            }
        }
        Assertions.assertFalse(isStableConnection);
        Assertions.assertTrue(isFaultyConnection);
    }

    @Test
    void popularCommandExecutorIllegalArguments() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () ->
                new PopularCommandExecutor(null, 5));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () ->
                new PopularCommandExecutor(new DefaultConnectionManager(), 0));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () ->
                new PopularCommandExecutor(new DefaultConnectionManager(), -1));
    }

    @Test
    void popularCommandExecutorWithDefaultConnectionManagerCanExecuteSuccessfully() {
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(), BIG_NUMBER_FOR_BIG_CHANCE);
        boolean isSuccessfully = false;
        try {
            popularCommandExecutor.updatePackages();
            isSuccessfully = true;
        } catch (Exception ignored) {

        }
        Assertions.assertTrue(isSuccessfully);
    }

    @Test
    void popularCommandExecutorWithDefaultConnectionManagerMayThrowException() {
        boolean isException = false;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 1);
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            try {
                popularCommandExecutor.updatePackages();
            } catch (Exception e) {
                isException = true;
                Assertions.assertInstanceOf(edu.hw2.Task3.ConnectionException.class, e.getCause());
                break;
            }
        }
        Assertions.assertTrue(isException);
    }

    @Test
    void popularCommandExecutorWithFaultyConnectionManagerCanExecuteSuccessfully() {
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new FaultyConnectionManager(), BIG_NUMBER_FOR_BIG_CHANCE);
        boolean isSuccessfully = false;
        try {
            popularCommandExecutor.updatePackages();
            isSuccessfully = true;
        } catch (Exception ignored) {

        }
        Assertions.assertTrue(isSuccessfully);
    }

    @Test
    void popularCommandExecutorWithFaultyConnectionManagerMayThrowException() {
        boolean isException = false;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        for (int i = 0; i < BIG_NUMBER_FOR_BIG_CHANCE; i++) {
            try {
                popularCommandExecutor.updatePackages();
            } catch (Exception e) {
                isException = true;
                Assertions.assertInstanceOf(edu.hw2.Task3.ConnectionException.class, e.getCause());
                break;
            }
        }
        Assertions.assertTrue(isException);
    }
}
