package com.logicbig.example;

import java.util.stream.DoubleStream;

public class SumExample {
    public static void main (String[] args) {
        runSum();
        runEquivalentReduce();
    }

    private static void runSum () {
        double sum = DoubleStream.of(1.1, 1.5, 2.5, 5.4).sum();
        System.out.println(sum);
    }

    private static void runEquivalentReduce () {
        double sum = DoubleStream.of(1.1, 1.5, 2.5, 5.4)
                                 .reduce(0, Double::sum);
        System.out.println(sum);
    }
}
