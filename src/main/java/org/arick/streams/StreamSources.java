package org.arick.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamSources {
    public static void main(String[] args) {
        StreamSources mySources = new StreamSources();
        mySources.someMethod();
    }

    void someMethod() {
        int[] integerArray = {1, 2, 3, 4, 5, 6};
        IntStream streamOfInteger = Arrays.stream(integerArray);
        streamOfInteger.filter(currentInteger -> currentInteger > 0).forEach(System.out::print);
        System.out.println();

        Collection<String> collection = Arrays.asList("a", "b", "c");
        List<String> listOfString = List.of("A", "B", "C", "D", "E", "F");
        listOfString.forEach(System.out::print);
        System.out.println();

        String[] stringArray = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Stream<String> streamOfString = Arrays.stream(stringArray, 2, 5);
        printStream(streamOfString);
        System.out.println();
        Stream<String> partialStreamOfString = Arrays.stream(stringArray, 2, 5);
        printStream(partialStreamOfString);
        List<String> containsJustB = Stream.of(stringArray)
                .filter(currentCharacter -> currentCharacter.contains("B"))
                .collect(Collectors.toList());
        containsJustB.forEach(System.out::println);

        Optional<String> anyElement = listOfString.stream().findAny();
        System.out.println("findAny[" + anyElement.orElse("Empty") +"]");
        Optional<String> firstElement = listOfString.stream().parallel().findAny();
        System.out.println("findFirst[" + anyElement.orElse("Empty") +"]");

        Stream<String> streamOfCollection = collection.stream();
        Stream<String> streamOfList = listOfString.stream();
        IntStream intStream = IntStream.range(1, 10);
        LongStream steamOfLong = LongStream.rangeClosed(1L, 10L);
    }

    void printStream(Stream<String> stream) {
        stream.filter(currentCharacter -> true)
                .forEach(System.out::print);
    }
}

