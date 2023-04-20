package org.arick.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LimitExamples {

    private static final int MAX_SIZE = 5;

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, MAX_SIZE, 6};
        IntStream intStream = IntStream.range(1, 10);


        System.out.printf("Source: %s%n", Arrays.toString(ints));
        System.out.println();
        System.out.println("Finding even numbers.");
        runWithoutLimit(Arrays.stream(ints));
        runWithoutLimit(intStream);
        runWithLimit01(Arrays.stream(ints));
        runWithAnIterator1();
        runWithAnIterator2();
    }

    private static void runWithoutLimit(IntStream stream) {
        System.out.println("Running without limit()");

        //filter even numbers
        stream.filter(currentInteger -> isAnEvenNumber(currentInteger))
              .forEach(System.out::println);
    }

    private static void runWithLimit01(IntStream stream) {
        int limit = 2;
        System.out.println("Running with limit of " + limit);
        //filter even numbers
        stream.filter(currentInteger -> isAnEvenNumber(currentInteger))
              .limit(2)
              .forEach(System.out::println);
    }

    private static void runWithAnIterator1() {
        System.out.println("Running with Iterator(1)");
        Stream<Integer> stream = Stream.iterate(1, currentInteger -> currentInteger + 1);
        stream.filter(currentInteger -> isAnEvenNumber(currentInteger))
              .limit(MAX_SIZE)
              .forEach(System.out::println);
    }

    private static void runWithAnIterator2() {
        System.out.println("Running with Iterator(2)");
        Stream<Integer> stream = Stream.iterate(1, currentInteger -> currentInteger + 1);
        stream.limit(MAX_SIZE)
              .filter(currentInteger -> isAnEvenNumber(currentInteger))
              .forEach(System.out::println);
    }

    private static boolean isAnEvenNumber(int currentInteger) {
        return currentInteger % 2 == 0;
    }

    public Stream<Integer> streamOf(List<Integer> integerList) {
        return integerList == null || integerList.isEmpty() ? Stream.empty() : integerList.stream();
    }
}

