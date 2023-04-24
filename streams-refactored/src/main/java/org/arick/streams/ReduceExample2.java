package org.arick.streams;

import java.util.stream.IntStream;

public class ReduceExample2 {
    private static final int END_EXCLUSIVE = 6;
    private static final int MULTIPLICATION_IDENTITY = 1; // returned if there are no elements in the range
    private static final int START_INCLUSIVE = 1;

    public static void main(String[] args) {
        accumulatedValueWithAValidRange();
        accumulatedValueWithAnEmptyRange();
    }

    private static void accumulatedValueWithAValidRange() {
        int accumulatedValue = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                                    .parallel()
                                    .reduce(MULTIPLICATION_IDENTITY,
                                            (accumuatorOperand1, accumulatorOperand2) -> accumuatorOperand1 *
                                                                                           accumulatorOperand2);
        System.out.println("Accumulated value, with a valid range: " + accumulatedValue);
    }

    private static void accumulatedValueWithAnEmptyRange() {
        int accumulatedValue = IntStream.empty()
                                        .parallel()
                                        .reduce(MULTIPLICATION_IDENTITY,
                                                (accumuatorOperand1, accumulatorOperand2) -> accumuatorOperand1 *
                                                                                               accumulatorOperand2);
        System.out.println("Accumulated value, with an empty range: " + accumulatedValue);
    }
}
