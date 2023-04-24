package org.arick.streams;

import java.util.stream.LongStream;

public class AverageExample {

    private static final int END_EXCLUSIVE = 10;
    private static final int START_INCLUSIVE = 1;
    private static final int OTHER = -1;

    public static void main(String[] args) {
        double results = LongStream.range(START_INCLUSIVE, END_EXCLUSIVE).average().orElse(OTHER);
        System.out.println(results);
    }
}
