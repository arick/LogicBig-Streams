package org.arick.streams;

import java.util.Optional;
import java.util.stream.Stream;


public class MaxExample {

    public static void main(String[] args) {
        runMax();
        runEquivalentReduce();
    }

    private static void runMax() {
        String maximumStringElement = Stream.of("banana", "pie", "apple")
                         .max(String::compareTo) // dictionary order
                         .orElse("None");

        System.out.println(maximumStringElement);
    }

    private static void runEquivalentReduce() {
        Optional<String> maximumStringElement = Stream.of("apple", "banana", "pie").
                                        reduce((stringElement1, stringElement2)
                                                       -> stringElement1.compareTo(stringElement2) > 0
                                                          ? stringElement1
                                                          : stringElement2);
        System.out.println(maximumStringElement.get());
    }
}
