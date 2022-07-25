package com.sun.livestream;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class SubtractTest extends BaseTest{

    @Test
    void subtract(){

        int result = calculator.subtract(10,10,10);
        assertThat("",result,is(lessThanOrEqualTo(100)));
    }
}
