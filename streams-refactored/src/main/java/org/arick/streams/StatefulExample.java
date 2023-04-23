package org.arick.streams;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class StatefulExample {
    private static final int ITERATION_LIMIT = 10;
    private static final int SLEEP_TIME_IN_MILLIS = 10;

    public static void main(String[] args) {
        for (int iteration = 0; iteration < ITERATION_LIMIT; iteration++) {
            sumParallelStream();
            sumSequentialStream();
        }
    }

    private static void sumParallelStream() {
        Set<Integer> seenInParallelStream = new HashSet<>();
        IntStream parallelStream = IntStream.of(1, 2, 1, 2, 3, 4, 4, 5);
        int sum = parallelStream.parallel().map(
                //stateful behavioral parameter.
                element -> {
                    try { // making it bit slow for more opportunity for thread interference
                        Thread.sleep(SLEEP_TIME_IN_MILLIS);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    if (seenInParallelStream.add(element)) return element; // this makes the operation stateful
                    else return 0; // won't change the value of sum
                }).sum();
        System.out.println("Parallel Sum: " + sum);
    }

    private static void sumSequentialStream() {
        Set<Integer> seenInSequentialStream = new HashSet<>();
        IntStream sequentialStream = IntStream.of(1, 2, 1, 2, 3, 4, 4, 5);
        int sum = sequentialStream.sequential().map(
                //stateful behavioral parameter.
                element -> {
                    try { // making it bit slow for more opportunity for thread interference
                        Thread.sleep(SLEEP_TIME_IN_MILLIS);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    if (seenInSequentialStream.add(element)) return element;
                    else return 0; // won't change the value of sum
                }).sum();
        System.out.println("Sequential Sum: " + sum);
    }


}
