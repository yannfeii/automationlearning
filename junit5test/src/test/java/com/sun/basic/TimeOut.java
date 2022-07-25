package com.sun.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.lang.Thread.sleep;

public class TimeOut {

    @Test
    @Timeout(3)
    void timeOutCase() throws InterruptedException {
        sleep(10000);
    }
}
