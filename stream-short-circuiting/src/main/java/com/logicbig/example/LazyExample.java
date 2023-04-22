package com.logicbig.example;

import java.util.stream.IntStream;

import static com.logicbig.example.LogUtil.log;

public class LazyExample {

    public static void main (String[] args) {
        IntStream stream = IntStream.range(1, 5);
        stream = stream.peek(i -> log("starting", i))
                       .filter(i -> {
                           log("filtering", i);
                           return i % 2 == 0;
                       })
                       .peek(i -> log("post filtering", i));
        log("Invoking terminal method count.");
        log("The count is", stream.count());
    }
}
