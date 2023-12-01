package edu.project2.Random;

import java.util.Queue;

public interface RandomGenerator {
    Queue<Integer> generate(int bound, int quantity);
}
