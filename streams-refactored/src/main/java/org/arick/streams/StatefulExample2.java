package org.arick.streams;


import java.util.stream.IntStream;

public class StatefulExample2 {
    private static final int ITERATION_LIMIT = 5;
    private static int count = 0;

    public static void main(String[] args) {
        for (int iteration = 0; iteration < ITERATION_LIMIT; iteration++) process();
    }

    private static void process() {
        count = 0;

        IntStream stream = IntStream.range(1, 1000);
        // finding the sum of even numbers
        int sum = stream.parallel()
                        .filter(element -> {
                            boolean elementIsAnEvenNumber = element % 2 == 0;
                            if (elementIsAnEvenNumber) {
                                count++; // count is external, thus updating count makes this operation stateful.
                            }
                            return elementIsAnEvenNumber;
                        })
                        .sum();

        System.out.printf("sum :%d  count:%d%n", sum, count);
    }
}
