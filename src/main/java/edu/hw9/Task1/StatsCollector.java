package edu.hw9.Task1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector {
    List<Metric> metrics = new CopyOnWriteArrayList<>();

    private static final int COUNT_OF_THREADS = 4;
    ExecutorService threads = Executors.newFixedThreadPool(COUNT_OF_THREADS);

    public void push(String metricName, double[] data) {
        threads.submit(() -> formMetric(metricName, data));
    }

    private void formMetric(String metricName, double[] data) {
        double sum = Arrays
            .stream(data)
            .sum();
        double average = sum / Arrays
            .stream(data)
            .count();
        double max = Arrays
            .stream(data)
            .max()
            .orElseThrow();
        double min = Arrays
            .stream(data)
            .min()
            .orElseThrow();
        metrics.add(new Metric(metricName, sum, average, max, min));
    }

    public List<Metric> stats() throws InterruptedException {
        threads.shutdown();
        threads.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        return metrics;
    }

}
