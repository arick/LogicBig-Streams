package com.logicbig.example;

import java.util.Optional;
import java.util.stream.Stream;

public class MinExample {
    public static void main (String[] args) {
        runMin();
        runEquivalentReduce();
    }

    private static void runMin () {
        String s = Stream.of("banana", "pie", "apple")
                         .min(String::compareTo) //dictionary order
                         .orElse("None");

        System.out.println(s);
    }

    private static void runEquivalentReduce () {
        Optional<String> reduce = Stream.of("apple", "banana", "pie")
                                        .reduce((s, s2) -> s.compareTo(s2) <= 0 ? s : s2);
        System.out.println(reduce.get());
    }
}
