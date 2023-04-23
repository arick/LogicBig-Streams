package org.arick.streams;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Even though this is thread safe, but the result is non-deterministic
 */
public class SideEffectWrongUse2Fix {
    public static void main(String[] args) {

        int[] lengthOfTheStrings = Stream.of("Banana", "Pear", "Apple")
                              .peek(SideEffectWrongUse2Fix::longTask)//applying side effect
                              .parallel()
                              .mapToInt(element -> element.length())
                              .toArray();
        System.out.println(Arrays.toString(lengthOfTheStrings));
    }

    private static void longTask(String s) {
        try {//some stateless task simulation. e.g. sending email
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
