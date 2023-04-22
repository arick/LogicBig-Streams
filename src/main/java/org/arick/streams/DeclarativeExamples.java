package org.arick.streams;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

public class DeclarativeExamples {

    private static final List<Integer> INTEGER_LIST = List.of(3, 2, 12, 5, 6, 11, 13);
    private static final List<String> FRUIT_LIST = List.of("Apple", "Orange", "Banana");

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        printFruit();
        countEvenIntegers();
        getListOfEvenIntegers();
        testIfAllIntsInListAreUnderTen();
        findSubDirectories();
    }

    private static void printFruit() {
        System.out.printf("\n=== printFruit ===%n");
        //using lambda expression
        FRUIT_LIST.forEach(currentFruit -> System.out.printf("%s ", currentFruit));
        System.out.println();
        //or using method reference on System.out instance
        FRUIT_LIST.forEach(System.out::print);
    }

    private static void countEvenIntegers() {
        System.out.printf("\n=== countEvenIntegers ===%n");
        long count = INTEGER_LIST.stream()
                                 .filter(currentInteger -> currentInteger % 2 == 0)
                                 .count();
        System.out.println(count);
    }

    private static void getListOfEvenIntegers() {
        System.out.printf("\n=== getListOfEvenIntegers ===%n");
        List<Integer> evenList = INTEGER_LIST.stream()
                                             .filter(currentInteger -> currentInteger % 2 == 0)
                                             .collect(Collectors.toList());
        evenList.forEach(System.out::println);
        System.out.println(evenList);
        INTEGER_LIST.stream()
                    .filter(currentInteger -> currentInteger % 2 == 0)
                    .forEach(System.out::println);
    }

    private static void testIfAllIntsInListAreUnderTen() {
        System.out.printf("\n=== testIfAllIntsInListAreUnderTen ===%n");
        boolean results = INTEGER_LIST.stream()
                                      .allMatch(currentInteger -> currentInteger < 10);
        System.out.printf("The assertion that all ints in INTEGER_LIST are under ten is %b.%n", results);
    }

    private static void findSubDirectories() {
        System.out.printf("\n=== findSubDirectories ===%n");
        List<String> allDirNames = stream(requireNonNull(new File("/tmp").listFiles()))
                .filter(File::isDirectory)
                .map(File::getName)
                .collect(Collectors.toList());
        System.out.println(allDirNames);
    }
}
