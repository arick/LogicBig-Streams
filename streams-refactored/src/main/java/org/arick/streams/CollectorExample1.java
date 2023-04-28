package org.arick.streams;

import java.util.List;

/*
 * <R> R collect(Supplier<R> supplier,
                 BiConsumer<R,? super T> accumulator,
                 BiConsumer<R,R> combiner)
 *
 * The Stream collect() methods reduces stream element of type T to a mutable result container of type R.
 * supplier: this function creates a a new result container. In sequential execution it's called only once, whereas, for parallel execution, it may be called multiple times to get a new instance for different parallel threads.
 * accumulator: an associative function to incorporate the current element to the result object (the result object is created in supplier function)
 * combiner: in parallel execution this function combines the results received from different threads. This must be associative function.
 *
 * IntStream, LongStream and DoubleStream also provide a collect() method.
 *
 */
public class CollectorExample1 {
    public static void main(String[] args) {
        collectViaExistingClass();
        immutableReduction();
    }

    private static void collectViaExistingClass() {
        List<String> list = List.of("Mike", "Nicki", "John");
        String collectedString = list.stream().collect(StringBuilder::new,
                                                       (stringContainer, stringElement) -> {
                                                           System.out.println("accumulator function call");
                                                           stringContainer.append(" ")
                                                                          .append(stringElement);
                                                       },
                                                       (stringBuilder1, stringBuilder2) -> {
                                                           System.out.println("combiner function call");
                                                           stringBuilder1.append(stringBuilder2.toString());
                                                       }).toString();
        System.out.println("Collect operation via existing class <String>: " + collectedString);
    }

    /*
     * This `reduce()` operation will be less efficient in performance than the `collect()` operation,
     * specially for large number of stream elements.
     * That's because the `collect()` method creates only one instance of the container object
     * rather than creating a new result for each iteration.
     */
    private static void immutableReduction() {
        List<String> list = List.of("Mike", "Nicki", "John");
        String reducedString = list.stream().reduce("", (string1, string2) -> string1 + " " + string2);
        System.out.println("Reduction operation via existing class <String>: " + reducedString);
    }
}
