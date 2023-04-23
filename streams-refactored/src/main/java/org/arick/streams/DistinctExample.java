package org.arick.streams;

import java.util.stream.IntStream;


public class DistinctExample {

    private static final int END_EXCLUSIVE = 1000000;
    private static final int START_INCLUSIVE = 0;

    public static void main(String[] args) {
        PerformanceTestUtil.runTest("unordered stream", () -> {
            IntStream stream = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE);
            stream.unordered().parallel().distinct().count();
        });

        PerformanceTestUtil.runTest("ordered stream", () -> {
            IntStream stream = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE);
            stream.parallel().distinct().count();
        });
    }
}
