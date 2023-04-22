package org.arick.streams;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

public class SequentialParallelComparison {

    public static void main(String[] args) {
        String[] strings = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

        System.out.println("-------\nRunning sequential\n-------");
        run(Arrays.stream(strings).sequential());
        System.out.println("-------\nRunning parallel\n-------");
        run(Arrays.stream(strings).parallel());
    }

    public static void run(Stream<String> stream) {
        stream.forEach(currentString -> {
            System.out.println(LocalTime.now() + " - value: " + currentString + " - thread: " + Thread.currentThread().getName());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}