package com.logicbig.example;

import java.util.Arrays;
import java.util.stream.Stream;

public class DistinctStabilityExample {
    public static void main(String[] args) {
        Object[] myObjects;

        myObjects = createStream().parallel().distinct().toArray();
        System.out.printf("ordered distinct result 1: %s%n", Arrays.toString(myObjects));

        MyObject.c = 0;
        myObjects  = createStream().parallel().distinct().toArray();
        System.out.printf("ordered distinct result 2: %s%n", Arrays.toString(myObjects));

        MyObject.c = 0;
        myObjects  = createStream().unordered().parallel().distinct().toArray();
        System.out.printf("unordered distinct result 1: %s%n", Arrays.toString(myObjects));

        MyObject.c = 0;
        myObjects  = createStream().unordered().parallel().distinct().toArray();
        System.out.printf("unordered distinct result 2: %s%n", Arrays.toString(myObjects));
    }

    private static Stream<MyObject> createStream() {
        return Stream.of(new MyObject("a"), new MyObject("b"), new MyObject("c"), new MyObject("b"),
                         new MyObject("c"), new MyObject("c"), new MyObject("a"), new MyObject("b"));

    }

    private static class MyObject {
        private static int c = 0;
        private int id = ++c;
        private String str;

        public MyObject(String str) {
            this.str = str;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyObject myObject = (MyObject) o;
            return str != null ? str.equals(myObject.str) : myObject.str == null;
        }

        @Override
        public int hashCode() {
            return str != null ? str.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "MyObject{id=" + id + ", str='" + str + "\'}";
        }
    }
}
