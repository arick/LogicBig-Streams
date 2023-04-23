package org.arick.streams;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PerformanceTestUtil {
    private static final PrintStream originalPrintStream = System.out;
    private static PrintStream blankPrintStream = new PrintStream(new OutputStream() {
        public void write(int someIntCharacter) {}

        @Override
        public void write(byte[] bytes, int offset, int length) throws IOException {}
    });

    public static void runTest(String message, Runnable testRunner) {
        setPrintStreamDisabled(true);
        //run test quietly first time to avoid cold start false-positive result
        testRunner.run();
        setPrintStreamDisabled(false);

        long startTime = getTimeElapsed(0);
        testRunner.run();
        System.out.printf("%s time taken: %s%n", message, timeToString(getTimeElapsed(startTime)));
    }

    private static void setPrintStreamDisabled(boolean turnOffStreamOut) {
        if (turnOffStreamOut) {System.setOut(blankPrintStream);}
        else {System.setOut(originalPrintStream);}
    }

    private static long getTimeElapsed(long startTime) {
        return System.nanoTime() - startTime;
    }

    private static String timeToString(long nanos) {
        Optional<TimeUnit> first = Stream.of(DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS, MICROSECONDS)
                                         .filter(u -> u.convert(nanos, NANOSECONDS) > 0)
                                         .findFirst();
        TimeUnit unit = first.isPresent() ? first.get() : NANOSECONDS;
        double value = (double) nanos / NANOSECONDS.convert(1, unit);

        return String.format("%.4g %s", value, unit.name().toLowerCase());
    }

}
