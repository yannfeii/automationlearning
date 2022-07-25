package com.sun.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class RepeatTest {

    //  @Test // 如果要使用重复测试，就可以不使用@Test
    //    @RepeatedTest(10) // 注解内需要传递控制重复次数的参数
    // value 表示要重复几次
    // displayName 对应执行用例的displayname，
    // currentRepetition 第几次重复
    // totalRepetitions 代表总共要重复的次数

    //@RepeatedTest(3)
    @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat")
    void testCase(){
        System.out.println("Repeat test case");
    }
}
