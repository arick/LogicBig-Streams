package org.arick.streams;

import java.util.stream.Stream;

public class CountExample {

    private static final int SUM_IDENTITY = 0;
    private static final long INCREMENT_SUM_BY_ONE = 1L;

    public static void main(String[] args) {
        runCount();
        runEquivalentReduce();
        runEquivalentSum();
    }

    private static void runCount() {
        long c = Stream.of("banana", "pie", "apple").count();
        System.out.println(c);
    }

    private static void runEquivalentReduce() {
        long sum = Stream.of("banana", "pie", "apple")
                         .mapToLong(s -> INCREMENT_SUM_BY_ONE)
                         .reduce(SUM_IDENTITY, Long::sum);
        System.out.println(sum);
    }

    private static void runEquivalentSum() {
        long sum = Stream.of("banana", "pie", "apple")
                         .mapToLong(s -> INCREMENT_SUM_BY_ONE)
                         .sum();
        System.out.println(sum);
    }
}
