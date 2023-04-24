package org.arick.streams;

import java.util.stream.IntStream;

public class ReduceExample1 {
    private static final int END_EXCLUSIVE = 6;
    private static final int OTHER = -1;
    private static final int START_INCLUSIVE = 1;

    public static void main(String[] args) {
        accumulatedValueWithAValidRange();
        accumulatedValueWithAnEmptyRange();
    }

    private static void accumulatedValueWithAValidRange() {
        int accumulatedValue = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                                        .reduce((accumuatorOperand1, accumuatorOperand2) -> accumuatorOperand1 *
                                                                                            accumuatorOperand2)
                                        .orElse(OTHER);
        System.out.println("Accumulated value, with a valid range: " + accumulatedValue);
    }

    private static void accumulatedValueWithAnEmptyRange() {
        int accumulatedValue = IntStream.empty()
                                        .reduce((accumuatorOperand1, accumuatorOperand2) -> accumuatorOperand1 *
                                                                                            accumuatorOperand2)
                                        .orElse(OTHER);
        System.out.println("Accumulated value, with an empty range: " + accumulatedValue);
    }
}
