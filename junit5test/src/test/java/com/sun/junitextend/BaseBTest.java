package com.sun.junitextend;

import org.junit.jupiter.api.*;

public class BaseBTest extends BaseATest{

    @BeforeAll
    static void beforeBAll(){
        System.out.println("BaseB ---- BeforeAll");
    }

    @AfterAll
    static void afterBAll(){
        System.out.println("BaseB ---- AfterAll");
    }

    @BeforeEach
    void beforeBEach(){
        System.out.println("BaseB ---- BeforeEach");
    }

    @AfterEach
    void afterBEach(){
        System.out.println("BaseB ---- afterEach");
    }

    @Test
    void testSun3(){
        System.out.println("BaseB ---- testSun3");
    }

    @Test
    void testSun4(){
        System.out.println("BaseB ---- testSun4");
    }
}
