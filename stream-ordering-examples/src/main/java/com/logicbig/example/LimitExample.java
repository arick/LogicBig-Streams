package com.logicbig.example;

import java.util.stream.IntStream;

public class LimitExample {

    public static void main (String[] args) {

        PerformanceTestUtil.runTest("unordered parallel stream limit test", () -> {
            IntStream stream = IntStream.range(0, 1000000000);
            stream.unordered()
                  .parallel()
                  .filter(i -> i % 2 == 0)
                  .limit(100000000)
                  .count();
        });

        PerformanceTestUtil.runTest("ordered parallel stream limit test", () -> {
            IntStream stream = IntStream.range(0, 1000000000);
            stream.parallel()
                  .filter(i -> i % 2 == 0)
                  .limit(100000000)
                  .count();
        });
    }
}