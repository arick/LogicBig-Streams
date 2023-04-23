package org.arick.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SideEffectWrongUse1 {

    private static final int END_EXCLUSIVE = 150;
    private static final int START_INCLUSIVE = 0;

    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();
        try {
            IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                     .parallel()
                     .filter(s -> s % 2 == START_INCLUSIVE)
                     .forEach(s -> results.add(s));//stateful side effect
            //not thread safe
            System.out.println(results);
        } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
            System.err.println("This is why we don't do operations with stateul side-effects in a stream.");
            exception.printStackTrace();
        }
    }
}
