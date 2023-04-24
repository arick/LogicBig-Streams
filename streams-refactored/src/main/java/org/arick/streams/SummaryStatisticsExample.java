package org.arick.streams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class SummaryStatisticsExample {
    public static void main(String[] args) {
        IntSummaryStatistics s = IntStream.rangeClosed(1, 10)
                                          .summaryStatistics();
        System.out.println(s);
    }
}
