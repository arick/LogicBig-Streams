package org.arick.streams;

import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.stream.Stream;

public class DistinctStabilityExample {
    public static void main(String[] args) {
        Object[] myObjects;
        int iterationMax = 2;

        for (int iteration = 1; iteration <= iterationMax; ++iteration) {
            MyObject.resetIdGenerator();
            myObjects = createStream().parallel().distinct().toArray();
            System.out.printf("ordered distinct   result %d: %s%n", iteration, Arrays.toString(myObjects));
        }

        for (int iteration = 1; iteration <= iterationMax; ++iteration) {
            MyObject.resetIdGenerator();
            myObjects = createStream().unordered().parallel().distinct().toArray();
            System.out.printf("unordered distinct result %d: %s%n", iteration, Arrays.toString(myObjects));
        }
    }

    private static Stream<MyObject> createStream() {
        return Stream.of(new MyObject("a"), new MyObject("b"), new MyObject("c"), new MyObject("b"),
                         new MyObject("c"), new MyObject("c"), new MyObject("a"), new MyObject("b"));
    }

    @EqualsAndHashCode
    private static class MyObject {
        private static int lastId = 0;

        final private int id = ++lastId;
        private String someString;

        public MyObject(String someString)    {this.someString = someString;}

        public static void resetIdGenerator() {lastId = 0;}

        /* The Lombok @toString is too 'chatting', i.e. cluttered. */
        @Override
        public String toString() {
            return "{id=" + id + ", str='" + someString + "\'}";
        }
    }
}
