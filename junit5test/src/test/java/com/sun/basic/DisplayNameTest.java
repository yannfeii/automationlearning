package com.sun.basic;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
public class DisplayNameTest {

    @Test
    void testCase1(){
        System.out.println("Test Case 1");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1","test2","test3"})
    void testCase2(String name){
        System.out.println(name);
        assertEquals(name.length(),5);
    }

    @Test
    void test_Case_3(){
        System.out.println("Test Case 3");
    }
}
