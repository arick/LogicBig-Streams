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
        doEvaluateStreamWithFilterFirst();
        doEvaluateStreamWithLimitFirst();
        findFirst();
        findAny();
        findAnyMatch();
        doAllMatch();
        doNoneMatch();
    }

    private static void doLazyEvaluationOnASequentialStream() {
        System.out.printf("%ndoLazyEvaluationOnASequentialStream%n");
        evaluateStream(IntStream.range(1, 13).sequential()); // default stream
    }

    private static void doLazyEvaluationOnAParallelStream() {
        System.out.printf("%ndoLazyEvaluationOnAParallelStream%n");
        evaluateStream(IntStream.range(1, 13).parallel());
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

    private static void doEvaluateStreamWithFilterFirst() {
        System.out.printf("%ndoEvaluateStreamWithFilterFirst%n");
        IntStream stream = IntStream.range(1, 13).sequential(); // default stream
        evaluateStreamWithFilterFirst(stream);
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
                       .peek(currentInteger -> log("post limit", currentInteger, "was found before the limit was exceeded."));
        log("Invoking terminal method count.");
        log("The included count is", stream.count());
    }

    private static void doEvaluateStreamWithLimitFirst() {
        System.out.printf("%ndoEvaluateStreamWithLimitFirst%n");
        IntStream stream = IntStream.range(1, 13).sequential(); // default stream
        evaluateStreamWithLimitFirst(stream);
    }

    private static void evaluateStreamWithLimitFirst(IntStream stream) {
        stream = stream.peek(currentInteger -> log("starting", currentInteger))
                       .limit(MAX_SIZE)
                       .peek(currentInteger -> log("post limit", currentInteger, "was found before the limit was exceeded."))
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

    private static void findFirst() {
        System.out.printf("%nfindFirst%n");
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6);
        stream = stream.filter(currentInteger -> currentInteger % 3 == 0);
        OptionalInt optionalInt = stream.findFirst();
        System.out.printf("\tstream.findAny found the value %d.%n", optionalInt.getAsInt());
    }

    private static void findAny() {
        System.out.printf("%nfindAny%n");
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                    .parallel();
        stream = stream.filter(currentInteger -> currentInteger % 2 == 0);
        OptionalInt optionalInt = stream.findAny();
        System.out.printf("\tstream.findAny found the value %d.%n", optionalInt.getAsInt());
    }

    private static void findAnyMatch() {
        System.out.printf("%nfindAnyMatch%n");
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        final String searchString = "our";
        System.out.printf("\tThe assertion that any (i.e. least one) of the source strings contain \"%s\", is %b.%n",
                          searchString,
                          stream.anyMatch(CurrentString -> CurrentString.contains(searchString)));
    }

    private static void doAllMatch() {
        System.out.printf("%ndoAllMatch");
        Stream<String> stream = Stream.of("one", "two", "Three", "four");
        boolean match = stream
                .allMatch(currentString -> currentString.length() > 0 &&
                                           Character.isLowerCase(currentString.charAt(0)));
        System.out.printf("\tThe assertion that the first character of all the source strings a lower case letter is %b.%n", match);
    }

    private static void doNoneMatch() {
        System.out.printf("%ndoNoneMatch");
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        boolean match = stream
                .noneMatch(currentString -> currentString.length() > 0 &&
                                            Character.isUpperCase(currentString.charAt(0)));
        System.out.printf("\tThe assertion that none of source strings start with an upper chase letter is %b.%n", match);
    }

    public static void log(Object... objects) {
        StringBuilder now = new StringBuilder(LocalTime.now().toString());
        for (Object object : objects) {
            now.append(" - ").append(object.toString());
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
