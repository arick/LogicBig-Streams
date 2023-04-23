package org.arick.streams;

import java.util.stream.IntStream;

public class SideEffectWithPeek {

    private static final int END_EXCLUSIVE = 5;
    private static final int START_INCLUSIVE = 0;

    public static void main(String[] args) {
        IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                 .unordered()
                 .parallel()
                 .map(x -> x * 2)
                 .peek(System.out::println) // Non-stateful, so okay
                 .count();
    }
}
