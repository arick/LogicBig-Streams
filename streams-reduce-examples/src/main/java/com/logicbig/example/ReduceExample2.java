package com.logicbig.example;

import java.util.stream.IntStream;

public class ReduceExample2 {
    public static void main (String[] args) {
        int i = IntStream.range(1, 6)
                         .parallel()
                         .reduce(1, (a, b) -> a * b);

        System.out.println(i);
    }
}
