package edu.hw7.Task2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class Task2 {

    public static BigInteger countFactorial(int num) {
        List<BigInteger> bigIntegers = new ArrayList<>();
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).limit(num).forEach(bigIntegers::add);
        return bigIntegers.parallelStream().reduce(BigInteger::multiply).orElse(BigInteger.valueOf(0));
    }

    private Task2() {
    }
}
