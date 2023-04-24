package org.arick.streams;

import java.util.Optional;
import java.util.stream.Stream;

public class MinExample {
    public static void main (String[] args) {
        runMin();
        runEquivalentReduce();
    }

    private static void runMin () {
        String minimumStringElement = Stream.of("banana", "pie", "apple")
                         .min(String::compareTo) // dictionary order
                         .orElse("None");

        System.out.println(minimumStringElement);
    }

    private static void runEquivalentReduce () {
        Optional<String> minimumStringElement = Stream.of("apple", "banana", "pie")
                                        .reduce((stringElement1, stringElement2)
                                                        -> stringElement1.compareTo(stringElement2) <= 0
                                                           ? stringElement1
                                                           : stringElement2);
        System.out.println(minimumStringElement.get());
    }
}
