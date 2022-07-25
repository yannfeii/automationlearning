package com.sun.livestream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DisplayName("加法运算")
public class SumTest extends BaseTest{

    static Stream<Arguments> sumCase3(){
        return Stream.of(
                Arguments.arguments(1,1,2),
                Arguments.arguments(2,3,5)
        );
    }

    @Test
    void sum() {
        Integer result = calculator.sum(10,10);
        logger.info("计算结果:result"+result);
        //assertEquals(20,result);
        assertThat("",result,is(equalTo(200)));

    }

    @Test
    void sumCase2() {
        //assertThrowsExactly()只能写具体的异常
        //Exception exception = assertThrowsExactly(NumberFormatException.class,()->calculator.sum(100,10));
        Exception exception = assertThrows(RuntimeException.class,()->calculator.sum(100,10));
        String result = exception.getMessage();
        assertThat("Exception Assert",result, is(containsString("integer is 100！")));

    }

    //@ParameterizedTest(name = "{argumentsWithNames}")
    //@ParameterizedTest(name = "{0} + {1} = {2}")
    //@ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @ParameterizedTest
    @MethodSource
    void sumCase3(int a,int b, int c) {

        int result = calculator.sum(a,b);
        assertThat("Exception Assert",result, is(equalTo(c)));

    }
}
