package org.arick.streams;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeclarativeExamples {

    private static final List<Integer> INTEGER_LIST = List.of(3, 2, 12, 5, 6, 11, 13);
    private static final List<String> FRUIT_LIST = List.of("Apple", "Orange", "Banana");

    public static void main(String[] args) {
        var myObject = new DeclarativeExamples();
        myObject.run();
    }

    public DeclarativeExamples() {
    }

    public static void run() {
        printFruit();
        countEvenIntegers();
        getListOfEvenIntegers();
        testIfAllIntsInListAreUnderTen();
        findSubDirectories();
    }

    private static void findSubDirectories() {
        List<String> allDirNames = Arrays.stream(new File("/tmp").listFiles())
                .filter(File::isDirectory)
                .map(File::getName)
                .collect(Collectors.toList());
        System.out.println(allDirNames);
    }


    private static void testIfAllIntsInListAreUnderTen() {
//        List<Integer> list = List.of(3, 2, 12, 5, 6, 11, 13);
        boolean results = INTEGER_LIST.stream()
                .allMatch(currentInteger -> currentInteger < 10);
        System.out.printf("The assertion that all ints in INTEGER_LIST are under ten is %b.%n", results);
    }

    private static void getListOfEvenIntegers() {
//        List<Integer> list = List.of(3, 2, 12, 5, 6, 11, 13);
        List<Integer> evenList = INTEGER_LIST.stream()
                .filter(currentInteger -> currentInteger % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenList);
        INTEGER_LIST.stream()
                .filter(currentInteger -> currentInteger % 2 == 0)
                .forEach(System.out::println);
    }

    private static void countEvenIntegers() {
        long count = INTEGER_LIST.stream()
                .filter(currentInteger -> currentInteger % 2 == 0)
                .count();
        System.out.println(count);
    }

    private static void printFruit() {
        //using lambda expression
        FRUIT_LIST.forEach(s -> System.out.println(s));
        //or using method reference on System.out instance
        FRUIT_LIST.forEach(System.out::println);
    }
}
