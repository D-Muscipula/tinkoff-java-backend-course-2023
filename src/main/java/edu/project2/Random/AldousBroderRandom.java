package edu.project2.Random;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class AldousBroderRandom implements RandomGenerator {
    @Override
    public Queue<Integer> generate(int bound, int quantity) {
        Queue<Integer> queue = new ArrayDeque<>();
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            queue.add(random.nextInt(bound + 1));
        }
        return queue;
    }
}
