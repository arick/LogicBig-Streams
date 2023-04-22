package com.logicbig.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ForEachExample {

    public static void main (String[] args) {
        final int[] ints = IntStream.range(0, 5).toArray();
        PerformanceTestUtil.runTest("forEach() method", () -> {
            Arrays.stream(ints).parallel().forEach(i -> doSomething(i));
        });

        PerformanceTestUtil.runTest("forEachOrdered() method", () -> {
            Arrays.stream(ints).parallel().forEachOrdered(i -> doSomething(i));
        });
    }

    private static void doSomething (int i) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s, ", i);
    }
}