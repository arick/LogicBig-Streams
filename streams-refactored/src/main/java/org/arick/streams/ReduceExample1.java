package org.arick.streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * BinaryOperator is a special type (sub-interface) of <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/BiFunction.html">BiFunction</a>
 * which takes two operands of the same type 'T' and returns a result of the same type T.
 *
 * The reduce() method iteratively applies the accumulator function on the current input element.
 *     Type Parameters:
 *     T - the type of the first argument to the function
 *     U - the type of the second argument to the function
 *     R - the type of the result of the function
 */
public class ReduceExample1 {
    private static final int END_EXCLUSIVE = 6;
    private static final int OTHER = -1;
    private static final int START_INCLUSIVE = 1;

    public static void main(String[] args) {
        accumulatedStringValue();
        accumulatedAdditionValueWithAValidRange();
        accumulatedMultiplicativeValueWithAValidRange();
        accumulatedMultiplicativeValueWithAnEmptyRange();
    }

    private static void accumulatedStringValue() {

        String accumulatedValue = new StringBuilder(Stream.of("1", "2", "3", "4", "5", "6")
                                                                 .reduce((streamOperand1, streamOperand2) ->
                                                                                 streamOperand1 + streamOperand2)
                                                                 .orElse("None")).toString();
        System.out.println("Accumulated value, with a String accumulator: " + accumulatedValue);
    }

    private static void accumulatedMultiplicativeValueWithAValidRange() {
        int accumulatedValue = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                                        .reduce((accumuatorOperand1, accumuatorOperand2) -> accumuatorOperand1 *
                                                                                            accumuatorOperand2)
                                        .orElse(OTHER);
        System.out.println("Accumulated value, with a valid range: " + accumulatedValue);
    }

    private static void accumulatedAdditionValueWithAValidRange() {
        int accumulatedValue = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                                        .reduce((accumuatorOperand1, accumuatorOperand2) -> accumuatorOperand1 +
                                                                                            accumuatorOperand2)
                                        .orElse(OTHER);
        System.out.println("Accumulated addition value, with a valid range: " + accumulatedValue);
    }

    private static void accumulatedMultiplicativeValueWithAnEmptyRange() {
        int accumulatedValue = IntStream.empty()
                                        .reduce((accumuatorOperand1, accumuatorOperand2) -> accumuatorOperand1 *
                                                                                            accumuatorOperand2)
                                        .orElse(OTHER);
        System.out.println("Accumulated value, with an empty range: " + accumulatedValue);
    }
}
