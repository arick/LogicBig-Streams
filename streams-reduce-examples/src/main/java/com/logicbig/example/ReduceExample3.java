package com.logicbig.example;

import java.util.stream.Stream;

public class ReduceExample3 {
    public static void main (String[] args) {
        int i = Stream.of("2", "3", "4", "5")
                      .parallel()
                      .reduce(0, (integer, s) -> Integer.sum(integer, Integer.parseInt(s)),
                              (integer, integer2) -> Integer.sum(integer, integer2));

        System.out.println(i);
    }
}
