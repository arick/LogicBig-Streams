package com.logicbig.example;

import java.util.stream.LongStream;

public class AverageExample {
    public static void main (String[] args) {
        double v = LongStream.range(1, 10).average().orElse(-1);
        System.out.println(v);
    }
}
