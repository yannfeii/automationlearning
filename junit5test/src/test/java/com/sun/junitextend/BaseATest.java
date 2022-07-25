package com.sun.junitextend;

import org.junit.jupiter.api.*;

public class BaseATest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("BaseA ---- BeforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("BaseA ---- AfterAll");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("BaseA ---- BeforeEach");
    }

    @AfterEach
    void afterEach(){
        System.out.println("BaseA ---- afterEach");
    }

    @Test
    void testSun1(){
        System.out.println("BaseA ---- testSun1");
    }

    @Test
    void testSun2(){
        System.out.println("BaseA ---- testSun2");
    }
}
