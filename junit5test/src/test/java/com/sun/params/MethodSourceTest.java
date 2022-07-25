package com.sun.params;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("stringProvide")
    void methodSourceDemo(String name){
        System.out.println("name "+name);

    }
    static Stream<String> stringProvide(){
        return Stream.of("apple","mango");
    }

    @ParameterizedTest
    @MethodSource
    void methodSourceMultiDemo(String name, Integer age){
        System.out.println(name+"的年龄是"+age);
    }

    static Stream<Arguments> methodSourceMultiDemo(){
        return Stream.of(
                Arguments.arguments("apple","18"),
                Arguments.arguments("mango","21")
                );

    }

    @Test
    public void test(){
//        String[] s=new String[10];
//        System.out.println(s[9]);
//        //System.out.println(s[10]);
//        System.out.println(s[0]);
//        System.out.println(s.length);
////        String str1 = new String("Good Good Study,Day Day up!");
////        int pos = str1.lastIndexOf("Day",15);
////        System.out.println(pos);
        String s = new String("hello");
        String t = new String("hello");
        char c [] = {'h','e','l','l','o'};
        System.out.println(s.equals(t));
        System.out.println(t.equals(c));
        System.out.println(s==t);
        System.out.println(t.equals(new String("hello")));
    }
}


