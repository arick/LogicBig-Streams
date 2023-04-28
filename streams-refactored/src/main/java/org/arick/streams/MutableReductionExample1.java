package org.arick.streams;

import java.util.Arrays;
import java.util.List;

public class MutableReductionExample1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Mike", "Nicki", "John");
        runMutableCollect(list);
        runImmutableReduce(list);
    }

    private static void runMutableCollect(List<String> list) {
        String collectedString = list.stream()
                                     .collect(StringBuilder::new,
                                              (stringBuilder, stringElement) -> stringBuilder.append(" ")
                                                                                             .append(stringElement),
                                              (stringBuilder1, stringBuilder2) -> stringBuilder1
                                                      .append(stringBuilder2.toString())).toString();
        System.out.println("Collect operation via existing class <String>: " + collectedString);
    }

    private static void runImmutableReduce(List<String> list) {
        String reducedString = list.stream().reduce("",
                                                    (stringElement1, stringElement2) -> stringElement1 + " " +
                                                                                        stringElement2);
        System.out.println("Reduction operation via existing class <String>: " + reducedString);
    }
}
