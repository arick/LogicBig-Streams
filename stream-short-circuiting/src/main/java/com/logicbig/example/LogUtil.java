package com.logicbig.example;

import java.time.LocalTime;

public class LogUtil {
    public static void log (Object... objects) {
        String s = LocalTime.now().toString();
        for (Object object : objects) {
            s += " - " + object.toString();
        }
        System.out.println(s);
        // just putting a little delay so that we can see a clear difference
        // with parallel stream
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}