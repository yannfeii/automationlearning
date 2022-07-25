package com.sun.basic;

import org.junit.jupiter.api.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.Random.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
public class OrderTest {

    @Test
    //@Order(2)
    @DisplayName("2")
    void OrderCase1(){
        System.out.println("OrderCase1");
    }

    @Test
    //@Order(1)
    @DisplayName("1")
    void OrderCase2(){
        System.out.println("OrderCase2");
    }

    @Test
    //@Order(3)
    @DisplayName("3")
    void OrderCase3(){
        System.out.println("OrderCase3");
    }
}
