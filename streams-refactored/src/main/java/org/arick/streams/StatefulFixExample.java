package org.arick.streams;

import java.util.stream.IntStream;

public class StatefulFixExample {

    private static final int ITERATION_LIMIT = 10;

    public static void main(String[] args) {
        for (int iteration = 0; iteration < ITERATION_LIMIT; iteration++) {
            IntStream stream = IntStream.of(1, 2, 1, 2, 3, 4, 4, 5);
            int sum = stream.parallel().distinct().sum();
            System.out.println(sum);
        }
    }
}
