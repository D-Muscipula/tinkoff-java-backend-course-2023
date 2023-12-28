package edu.hw9;

import edu.hw9.Task1.Metric;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task1Test {
    @Test
    void statsTest() {
        ExecutorService threads = Executors.newFixedThreadPool(2);
        StatsCollector collector = new StatsCollector();
        threads.submit(() -> collector.push("aboba", new double[] {0.1, 0.05, 1.4, 5.1, 0.3}));
        threads.submit(() -> collector.push("ab", new double[] {0.1, 1, 1.4, 3, 10}));
        threads.submit(() -> collector.push("a", new double[] {3, 5, 7, 5, 0}));
        threads.shutdown();
        List<Metric> metrics = new ArrayList<>(){{
            add(new Metric("aboba", Arrays.stream(new double[] {0.1, 0.05, 1.4, 5.1, 0.3}).sum()
                ,Arrays.stream(new double[] {0.1, 0.05, 1.4, 5.1, 0.3}).sum() / 5,
                5.1, 0.05));
            add(new Metric("ab", Arrays.stream(new double[] {0.1, 1, 1.4, 3, 10}).sum()
                ,Arrays.stream(new double[] {0.1, 1, 1.4, 3, 10}).sum() / 5,
                10, 0.1));
            add(new Metric("a", Arrays.stream(new double[] {3, 5, 7, 5, 0}).sum()
                ,Arrays.stream(new double[] {3, 5, 7, 5, 0}).sum() / 5,
                7, 0));
        }};
        try {
            threads.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
            for (var metric: collector.stats()) {
                Assertions.assertTrue(metrics.contains(metric));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
