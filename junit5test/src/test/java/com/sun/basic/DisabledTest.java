package com.sun.basic;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("禁用测试类")
public class DisabledTest {

    @Test
    //@Disabled
    @Disabled("禁用测试方法")
    void testCase1(){
        System.out.println("testCase1");
    }

    @Test
    void testCase2(){
        System.out.println("testCase2");
    }
}
