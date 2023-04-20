package org.arick.streams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImperativeExamples {
    private static final List<Integer> INTEGER_LIST = List.of(3, 2, 12, 5, 6, 11, 13);
    private static final List<String> FRUIT_LIST = List.of("Apple", "Orange", "Banana");
    public static void main(String[] args) {
        var myObject = new ImperativeExamples();
        myObject.run();
    }

    public static void run() {
        printFruit();
        countEvenIntegers();
        getListOfEvenIntegers();
        testIfAllIntsInListAreUnderTen();
        findSubDirectories();
    }

    private static void findSubDirectories() {
        List<String> allDirNames = new ArrayList<>();
        for (File file : new File("/tmp").listFiles()) {
            if (file.isDirectory()) {
                allDirNames.add(file.getName());
            }
        }
        System.out.println(allDirNames);
    }

    private static void testIfAllIntsInListAreUnderTen() {
        boolean results = true;
        for (Integer currentInteger : INTEGER_LIST) {
            if (currentInteger >= 10) {
                results = false;
                break;
            }
        }
        System.out.printf("The assertion that all ints in INTEGER_LIST are under ten is %b.%n", results);
    }

    private static void getListOfEvenIntegers() {
        List<Integer> evenList = new ArrayList<>();
        for (Integer currentInteger : INTEGER_LIST) {
            if (currentInteger % 2 == 0) {
                evenList.add(currentInteger);
            }
        }
        System.out.println(evenList);
    }

    private static void countEvenIntegers() {
        int count = 0;
        for (Integer currentInteger : INTEGER_LIST) {
            if (currentInteger % 2 == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void printFruit() {
        for (String s : FRUIT_LIST) {
            System.out.println(s);
        }
    }
}
