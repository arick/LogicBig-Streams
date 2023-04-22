package org.arick.streams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.*;

public class ImperativeExamples {
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
        for (String currentFruit : FRUIT_LIST) {
            System.out.printf("%s ", currentFruit);
        }
        System.out.println();
        for (String currentFruit : FRUIT_LIST) {
            System.out.print(currentFruit);
        }
    }

    private static void countEvenIntegers() {
        System.out.printf("\n=== countEvenIntegers ===%n");
        int count = 0;
        for (Integer currentInteger : INTEGER_LIST) {
            if (currentInteger % 2 == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void getListOfEvenIntegers() {
        System.out.printf("\n=== getListOfEvenIntegers ===%n");
        List<Integer> evenList = new ArrayList<>();
        for (Integer currentInteger : INTEGER_LIST) {
            if (currentInteger % 2 == 0) {
                evenList.add(currentInteger);
                System.out.println(currentInteger);
            }
        }
        System.out.println(evenList);
        for (Integer evenInteger : evenList) {
            System.out.println(evenInteger);
        }
    }

    private static void testIfAllIntsInListAreUnderTen() {
        System.out.printf("\n=== testIfAllIntsInListAreUnderTen ===%n");
        boolean results = true;
        for (Integer currentInteger : INTEGER_LIST) {
            if (currentInteger >= 10) {
                results = false;
                break;
            }
        }
        System.out.printf("The assertion that all ints in INTEGER_LIST are under ten is %b.%n", results);
    }

    private static void findSubDirectories() {
        System.out.printf("\n=== findSubDirectories ===%n");
        List<String> allDirNames = new ArrayList<>();
        for (File file : requireNonNull(new File("/tmp").listFiles())) {
            if (file.isDirectory()) {
                allDirNames.add(file.getName());
            }
        }
        System.out.println(allDirNames);
    }
}
