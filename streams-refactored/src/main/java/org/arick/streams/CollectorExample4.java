package org.arick.streams;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorExample4 {
    public static void main(String[] args) {
        List<String> collectedString = Stream.of("Mike", "Nicki", "John")
                               .parallel()
                               .collect(new SimpleCustomCollector());
        System.out.println("Collect operation via existing class <String>: " + collectedString);
    }

    private static class SimpleCustomCollector implements Collector<String, List<String>, List<String>> {
        @Override
        public Supplier<List<String>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<String>, String> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<String>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        @Override
        public Function<List<String>, List<String>> finisher() {
            return null;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.IDENTITY_FINISH);
        }
    }
}
