package org.arick.streams;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReduceExample3 {

    private static final int ADDITION_IDENTITY = 0;

    public static void main(String[] args) {
        accumulateUsingAnonymousFunctions();
        accumulateUsingLambdas();
    }

    private static void accumulateUsingAnonymousFunctions() {
        int i = Stream.of("2", "3", "4", "5")
                      .parallel()
                      .reduce(0, new BiFunction<Integer, String, Integer>() {
                          public Integer apply(Integer integerElement, String stringElement) {
                              return Integer.sum(integerElement, Integer.parseInt(stringElement));
                          }
                      }, new BinaryOperator<Integer>() {
                          public Integer apply(Integer combineElement1, Integer combineElement2) {
                              return Integer.sum(combineElement1, combineElement2);
                          }
                      });
        System.out.println(i);
    }

    private static void accumulateUsingLambdas() {
        int i = Stream.of("2", "3", "4", "5")
                      .parallel()
                      .reduce(ADDITION_IDENTITY,
                              (integerElement, stringElement) -> Integer.sum(integerElement,
                                                                             Integer.parseInt(stringElement)),
                              (combineElement1, combineElement2) -> Integer.sum(combineElement1, combineElement2));
        System.out.println(i);
    }
}
