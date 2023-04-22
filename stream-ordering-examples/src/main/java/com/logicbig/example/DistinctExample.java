package com.logicbig.example;

import java.util.stream.IntStream;


public class DistinctExample {

    public static void main (String[] args) {
        PerformanceTestUtil.runTest("unordered stream", () -> {
            IntStream stream = IntStream.range(0, 1000000);
            stream.unordered().parallel().distinct().count();
        });

        PerformanceTestUtil.runTest("ordered stream", () -> {
            IntStream stream = IntStream.range(0, 1000000);
            stream.parallel().distinct().count();
        });
    }
}