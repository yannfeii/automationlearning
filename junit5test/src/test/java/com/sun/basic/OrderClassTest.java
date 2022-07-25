package com.sun.basic;

import org.junit.jupiter.api.*;

//@TestClassOrder(ClassOrderer.OrderAnnotation.class)
//@TestClassOrder(ClassOrderer.Random.class)
//@TestClassOrder(ClassOrderer.ClassName.class)
//@TestClassOrder(ClassOrderer.DisplayName.class)
public class OrderClassTest {

    @Nested
    @Order(2)
    class class1{
        @Test
        void testCase(){
            System.out.println("classOne");
        }
    }

    @Nested
    @Order(1)
    class class2{
        @Test
        void testCase(){
            System.out.println("classTwo");
        }
    }

    @Nested
    @Order(3)
    class class3{
        @Test
        void testCase(){
            System.out.println("classThree");
        }
    }
}
