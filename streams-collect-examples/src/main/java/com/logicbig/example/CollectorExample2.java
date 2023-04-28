package com.logicbig.example;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorExample2 {
    public static void main (String[] args) {
        String s = Stream.of("Mike", "Nicki", "John")
                         .parallel()
                         .unordered()
                         .collect(new MyCollector());
        System.out.println(s);
    }

    private static class MyCollector implements
                                     Collector<String, StringBuffer, String> {

        @Override
        public Supplier<StringBuffer> supplier () {
            return () -> {
                System.out.println("supplier call");
                return new StringBuffer();
            };
        }

        @Override
        public BiConsumer<StringBuffer, String> accumulator () {
            return (sb, s) -> {
                System.out.println("accumulator function call,"
                                   + " accumulator container: "
                                   + System.identityHashCode(sb)
                                   + " thread: "
                                   + Thread.currentThread().getName()
                                   + ", processing: " + s);
                sb.append(" ").append(s);
            };
        }

        @Override
        public BinaryOperator<StringBuffer> combiner () {
            return (stringBuilder, s) -> {
                System.out.println("combiner function call");
                return stringBuilder.append(s);
            };
        }

        @Override
        public Function<StringBuffer, String> finisher () {
            return stringBuilder -> stringBuilder.toString();
        }

        @Override
        public Set<Characteristics> characteristics () {
            //  return Collections.emptySet();
            return EnumSet.of(Characteristics.CONCURRENT);
        }
    }
}
