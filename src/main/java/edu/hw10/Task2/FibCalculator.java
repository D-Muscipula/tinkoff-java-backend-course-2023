package edu.hw10.Task2;

public interface FibCalculator {
    @Cache(persist = true)
    long getFib(int number);

    @Cache
    long getFibPersistFalse(int number);
}
