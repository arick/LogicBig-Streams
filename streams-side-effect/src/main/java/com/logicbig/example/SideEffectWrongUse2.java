package com.logicbig.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Even though this is thread safe, but the result is non-deterministic
 */
public class SideEffectWrongUse2 {
    public static void main (String[] args) {
        List<Integer> lengths = Collections.synchronizedList(new ArrayList<>());
        Stream.of("Banana", "Pear", "Apple")
              .peek(SideEffectWrongUse2::longTask) // applying side effect
              .parallel()
              .mapToInt(s -> s.length())
              .forEach(lengths::add); // collecting via side effect
        // updating state
        System.out.println(lengths);
    }

    private static void longTask (String s) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
