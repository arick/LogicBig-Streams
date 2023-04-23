package org.arick.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SideEffectWrongUse1Fix {

    private static final int END_EXCLUSIVE = 1000;
    private static final int START_INCLUSIVE = 0;

    public static void main(String[] args) {
        IntStream stream = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE);
        List<Integer> list = stream.parallel()
                                   .filter(element -> element % 2 == START_INCLUSIVE)
                                   .boxed()
                                   .collect(Collectors.toList());
        System.out.println(list);
    }
}
