package edu.hw7.Task1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {

    private Task1() {
    }

    public static AtomicInteger changeNum(int number, int numbersOfThreads) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(number);
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numbersOfThreads; i++) {
            Thread thread = new Thread(count::getAndIncrement);
            threads.add(thread);
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        return count;
    }

}



