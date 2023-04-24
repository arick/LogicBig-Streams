package com.logicbig.example;

import java.util.stream.IntStream;

public class ReduceExample1 {
    public static void main (String[] args) {
        int i = IntStream.range(1, 6)
                         .reduce((a, b) -> a * b)
                         .orElse(-1);

        System.out.println(i);
    }
}
