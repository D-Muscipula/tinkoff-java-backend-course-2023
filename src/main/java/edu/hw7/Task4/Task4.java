package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public final class Task4 {

    private final static int MIN = -10000;
    private final static int MAX = 10000;

    private final static int FORMULA_NUMBER = 4;

    public static double getPI(int numberOfIterations) {
        int circleCount = singleCount(numberOfIterations);
        return FORMULA_NUMBER * ((double) circleCount / numberOfIterations);
    }

    public static double multithreadingGetPI(int numberOfIterations, int countOfThreads) {
        ArrayList<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < countOfThreads; i++) {
            futures.add(null);
        }
        try (ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads)) {
            for (int i = 0; i < countOfThreads; i++) {
                futures.set(i, executorService.submit(() -> (long) singleCount(numberOfIterations / countOfThreads)));
            }
            executorService.shutdown();
            boolean isExecuted = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            if (!isExecuted) {
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long circleCount = 0;
        for (int i = 0; i < countOfThreads; i++) {
            try {
                circleCount += futures.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return FORMULA_NUMBER * ((double) circleCount / numberOfIterations);
    }

    public static boolean isInTheCircle(double x, double y) {
        return (x * x + y * y) <= MAX * MAX;
    }

    public static int singleCount(int numberOfIterations) {
        int circleCount = 0;
        for (int i = 0; i < numberOfIterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble(MIN, MAX);
            double y = ThreadLocalRandom.current().nextDouble(MIN, MAX);
            if (isInTheCircle(x, y)) {
                circleCount++;
            }
        }
        return circleCount;
    }

    private Task4() {
    }
}
