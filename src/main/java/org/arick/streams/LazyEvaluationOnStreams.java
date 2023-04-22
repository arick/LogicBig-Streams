package org.arick.streams;

import java.time.LocalTime;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LazyEvaluationOnStreams {

    private static final int MAX_SIZE = 5;

    public static void main(String[] args) {
        doLazyEvaluationOnASequentialStream();
        doLazyEvaluationOnAParallelStream();
        doevaluateStreamWithFilterFirst();
        doevaluateStreamWithLimitFirst();
        findFirst();
        findAny();
        findAnyMatch();
        doAllMatch();
        doNoneMatch();
    }

    private static void doNoneMatch() {
        System.out.println("doNoneMatch");
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        boolean match = stream
                .noneMatch(currentString -> currentString.length() > 0 &&
                                            Character.isUpperCase(currentString.charAt(0)));
        System.out.println(match);
    }

    private static void doAllMatch() {
        System.out.println("doAllMatch");
        Stream<String> stream = Stream.of("one", "two", "Three", "four");
        boolean match = stream
                .allMatch(currentString -> currentString.length() > 0 &&
                                           Character.isLowerCase(currentString.charAt(0)));
        System.out.println(match);
    }

    private static void findAnyMatch() {
        System.out.println("findAnyMatch");
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        boolean match = stream.anyMatch(CurrentString -> CurrentString.contains("our"));
        System.out.println(match);
    }

    private static void findAny() {
        System.out.println("findAny");
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                    .parallel();
        stream = stream.filter(currentInteger -> currentInteger % 2 == 0);
        OptionalInt opt = stream.findAny();
        if (opt.isPresent()) {
            System.out.println(opt.getAsInt());
        }
    }

    private static void findFirst() {
        System.out.println("findFirst");
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6);
        stream = stream.filter(currentInteger -> currentInteger % 3 == 0);
        OptionalInt opt = stream.findFirst();
        if (opt.isPresent()) {
            System.out.println(opt.getAsInt());
        }
    }

    private static void doLazyEvaluationOnAParallelStream() {
        System.out.println("doLazyEvaluationOnAParallelStream");
        IntStream stream = IntStream.range(1, 9).parallel();
        evaluateStream(stream);
    }

    private static void doLazyEvaluationOnASequentialStream() {
        System.out.println("doLazyEvaluationOnASequentialStream");
        IntStream stream = IntStream.range(1, 9).sequential(); // default stream
        evaluateStream(stream);
    }

    private static void doevaluateStreamWithFilterFirst() {
        System.out.println("doevaluateStreamWithFilterFirst");
        IntStream stream = IntStream.range(1, 9).sequential(); // default stream
        evaluateStreamWithFilterFirst(stream);
    }

    private static void doevaluateStreamWithLimitFirst() {
        System.out.println("doevaluateStreamWithLimitFirst");
        IntStream stream = IntStream.range(1, 9).sequential(); // default stream
        evaluateStreamWithLimitFirst(stream);
    }

    private static void evaluateStream(IntStream stream) {
        stream = stream.peek(currentInteger -> log("starting", currentInteger))
                       .filter(currentInteger -> {
                           log("filtering", currentInteger);
                           return isAnEvenNumber(currentInteger);
                       })
                       .peek(currentInteger -> log("post filtering",
                                                   currentInteger,
                                                   "is an even number and will be included in the count."));
        log("Invoking terminal method count.");
        log("The included count is", stream.count());
    }

    private static void evaluateStreamWithFilterFirst(IntStream stream) {
        stream = stream.peek(currentInteger -> log("starting", currentInteger))
                       .filter(currentInteger -> {
                           log("filtering", currentInteger);
                           return isAnEvenNumber(currentInteger);
                       })
                       .peek(currentInteger -> log("post filtering",
                                                   currentInteger,
                                                   "is an even number and will be included in the count."))
                       .limit(MAX_SIZE)
                       .peek(currentInteger -> log("post limit",
                                                   currentInteger,
                                                   "is under the limit."));
        log("Invoking terminal method count.");
        log("The included count is", stream.count());
    }

    private static void evaluateStreamWithLimitFirst(IntStream stream) {
        stream = stream.peek(currentInteger -> log("starting", currentInteger))
                       .limit(MAX_SIZE)
                       .peek(currentInteger -> log("post limit",
                                                   currentInteger,
                                                   "is under the limit."))
                       .filter(currentInteger -> {
                           log("filtering", currentInteger);
                           return isAnEvenNumber(currentInteger);
                       })
                       .peek(currentInteger -> log("post filtering",
                                                   currentInteger,
                                                   "is an even number and will be included in the count."));
        log("Invoking terminal method count.");
        log("The included count is", stream.count());
    }

    public static void log(Object... objects) {
        String now = LocalTime.now().toString();
        for (Object object : objects) {
            now += " - " + object.toString();
        }
        System.out.println(now);
        // putting a little delay so that we can see a clear difference
        // with parallel stream.
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isAnEvenNumber(int currentInteger) {
        return currentInteger % 2 == 0;
    }
}
