package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class NewFixedThreadPool implements ThreadPool {
    private final int size;
    private final BlockingQueue<Runnable> tasks;
    private final Thread[] threads;

    private NewFixedThreadPool(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.size = size;
            this.tasks = new LinkedBlockingQueue<>(size);
            this.threads = new Thread[size];
        }

    }

    public static NewFixedThreadPool create(int size) {
        return new NewFixedThreadPool(size);
    }

    @Override
    public void start() {
        for (int i = 0; i < size; i++) {
            threads[i] = new WorkerThread(tasks);
            threads[i].start();
        }

    }

    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }
        try {
            tasks.put(command);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() throws Exception {

        for (Thread ignored : threads) {
            tasks.put(PoisonPill.INSTANCE);

        }
    }

    public void awaitTerminator() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private final static class WorkerThread extends Thread {
        private final BlockingQueue<Runnable> taskQueue;

        WorkerThread(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    if (task == PoisonPill.INSTANCE) {
                        break;
                    }
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        }
    }

    private final static class PoisonPill implements Runnable {
        private static final PoisonPill INSTANCE = new PoisonPill();

        @Override
        public void run() {
        }
    }
}
