package com.logicbig.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SideEffectWrongUseFix {
    public static void main (String[] args) {
        IntStream stream = IntStream.range(0, 1000);
        List<Integer> list = stream.parallel()
                                   .filter(s -> s % 2 == 0)
                                   .boxed()
                                   .collect(Collectors.toList());
        System.out.println(list);
    }
}
