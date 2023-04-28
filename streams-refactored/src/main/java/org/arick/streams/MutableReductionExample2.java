package org.arick.streams;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MutableReductionExample2 {

    private static final int END_EXCLUSIVE = 100;
    private static final int START_INCLUSIVE = 1;

    public static void main(String[] args) {
        IntStream stream = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE);
        List<Integer> collectedIntegers = stream.parallel()
                                                .filter(integerElement -> integerElement % 10 == 0)
                                                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Collect operation via existing class <String>: " + collectedIntegers);
    }
}
