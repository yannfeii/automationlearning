package com.sun.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {

    @Test
    void assertAllDemoTest(){
        System.out.println("assertAll Demo");
        assertAll("Number assert",
                ()->assertEquals(2,1+1),
                ()->assertEquals(3,1+1),
                ()->assertEquals(4,1+1)
        );
    }

    @Test
    void assertAllTest(){
        System.out.println("assertAll2");
        assertAll(()->assertEquals(2,1+1),
                ()->assertEquals(3,1+1),
                ()->assertEquals(4,1+1)
        );
    }

    @Test
    void assertTimeOutTest(){

        assertTimeout(Duration.ofSeconds(3),()->{
            sleep(4000);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1","test2","test3"})
    void testSingleParam(String name){
        System.out.println(name);
        assertEquals(name.length(),5);
    }

    @ParameterizedTest
    @CsvSource({"testCSV1,20","testCSV2,21","testCSV3,22"})
    void testMultiParam(String name,int age){
        System.out.println(name+" age is "+age);
    }

    @ParameterizedTest
    @CsvSource(value = {"testCSV1-20","testCSV2-21","testCSV3-22"},delimiterString = "-")
    void testMultiParam2(String name,int age){
        System.out.println(name+" age is "+age);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void testFileParam(String name,int age){
        System.out.println(name+" age is "+age);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data2.csv",delimiterString = "|")
    void testFileParam2(String name,int age){
        System.out.println(name+" age is "+age);
    }

    @ParameterizedTest
    @MethodSource()
    void testMethodParam(String name,int age){
        System.out.println(name+" age is "+age);
    }


}
