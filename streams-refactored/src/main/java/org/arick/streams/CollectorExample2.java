package org.arick.streams;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorExample2 {
    public static void main(String[] args) {
        String collectedString = Stream.of("Mike", "Nicki", "John")
                         .parallel()
                         .unordered()
                         .collect(new SimpleCustomCollector());
        System.out.println(collectedString);
    }

    private static class SimpleCustomCollector implements Collector<String, StringBuffer, String> {
        @Override
        public Supplier<StringBuffer> supplier() {
            return () -> {
                System.out.println("supplier call");
                return new StringBuffer();
            };
        }

        @Override
        public BiConsumer<StringBuffer, String> accumulator() {
            return (stringBuffer, stringElement) -> {
                System.out.println("accumulator function call,"
                                   + " accumulator container: "
                                   + System.identityHashCode(stringBuffer)
                                   + " thread: "
                                   + Thread.currentThread().getName()
                                   + ", processing: " + stringElement);
                stringBuffer.append(" ").append(stringElement);
            };
        }

        @Override
        public BinaryOperator<StringBuffer> combiner() {
            return (stringBuilder, stringElement) -> {
                System.out.println("combiner function call");
                return stringBuilder.append(stringElement);
            };
        }

        @Override
        public Function<StringBuffer, String> finisher() {
            return StringBuffer::toString;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.CONCURRENT);
        }
    }
}
