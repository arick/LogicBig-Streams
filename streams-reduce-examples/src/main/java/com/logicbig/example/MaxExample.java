package com.logicbig.example;

import java.util.Optional;
import java.util.stream.Stream;


public class MaxExample {

    public static void main (String[] args) {
        runMax();
        runEquivalentReduce();
    }

    private static void runMax () {
        String s = Stream.of("banana", "pie", "apple")
                         .max(String::compareTo) //dictionary order
                         .orElse("None");

        System.out.println(s);
    }

    private static void runEquivalentReduce () {
        Optional<String> reduce = Stream.of("apple", "banana", "pie")
                                        .reduce((s, s2) -> s.compareTo(s2) > 0 ? s : s2);
        System.out.println(reduce.get());
    }
}
