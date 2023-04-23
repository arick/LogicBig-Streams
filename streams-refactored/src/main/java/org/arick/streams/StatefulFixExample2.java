package org.arick.streams;

import java.util.stream.IntStream;


public class StatefulFixExample2 {

    private static final int END_EXCLUSIVE = 1000;
    private static final int ITERATION_LIMIT = 5;
    private static final int START_INCLUSIVE = 1;

    public static void main(String[] args) {
        for (int iteration = 0; iteration < ITERATION_LIMIT; iteration++) process();
    }

    private static void process() {
        IntStream stream = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE);

        //finding the even numbers
        int[] evenNumbers = stream.parallel()
                                  .filter(element -> element % 2 == 0)
                                  .toArray();

        // finding sum
        int sum = IntStream.of(evenNumbers).parallel().sum();

        System.out.printf("sum :%d  count:%d%n", sum, evenNumbers.length);
    }
}
