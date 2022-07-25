package com.sun.basic;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagTest {

    @Tag("preprod")
    @Test
    void testCase1(){
        System.out.println("预生产环境");
    }

    @Tag("test")
    @Test
    void testCase2(){
        System.out.println("测试环境");
    }

    @Tag("dev")
    @Test
    void testCase3(){
        System.out.println("开发环境");
    }

    @Tag("dev")
    @Tag("test")
    @Test
    void testCase4(){
        System.out.println("开发+测试环境");
    }

    @PreprodTag
    void testCase5(){
        System.out.println("pre prod");
    }
}
