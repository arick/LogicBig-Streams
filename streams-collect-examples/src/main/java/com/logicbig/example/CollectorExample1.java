package com.logicbig.example;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorExample1 {
    public static void main (String[] args) {
        String s = Stream.of("Mike", "Nicki", "John").collect(new
                                                                      MyCollector());
        System.out.println(s);
    }

    private static class MyCollector implements
                                     Collector<String, StringBuilder, String> {

        @Override
        public Supplier<StringBuilder> supplier () {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, String> accumulator () {
            return (sb, s) -> sb.append(" ").append(s);
        }

        @Override
        public BinaryOperator<StringBuilder> combiner () {
            return (sb1, sb2) -> sb1.append(sb2);
        }

        @Override
        public Function<StringBuilder, String> finisher () {
            return stringBuilder -> stringBuilder.toString();
        }

        @Override
        public Set<Characteristics> characteristics () {
            return Collections.emptySet();
        }
    }
}
