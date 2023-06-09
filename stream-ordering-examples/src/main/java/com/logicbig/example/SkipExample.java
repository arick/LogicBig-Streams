package com.logicbig.example;

import java.util.stream.IntStream;

public class SkipExample {

    public static void main (String[] args) {
        PerformanceTestUtil.runTest("unordered parallel skip", () -> {
            IntStream intStream = IntStream.range(1, 100000000);
            intStream.unordered().parallel().skip(1000).toArray();
        });

        PerformanceTestUtil.runTest("ordered parallel skip", () -> {
            IntStream intStream = IntStream.range(1, 100000000);
            intStream.parallel().skip(1000).toArray();
        });
    }
}