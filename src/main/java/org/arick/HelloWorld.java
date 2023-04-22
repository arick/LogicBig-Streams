package org.arick;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorld {
    public static void main(String... args) {
        LOG.info("This is a log message");
        System.out.println("Hello world!");
   }
}