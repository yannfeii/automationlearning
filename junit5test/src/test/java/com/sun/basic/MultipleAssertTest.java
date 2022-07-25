package com.sun.basic;

import com.sun.livestream.Calculator;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleAssertTest {

    @Test
    public void test1(){
        int addCount1 = Calculator.sum(1,4);
        int addCount2 = Calculator.sum(4,5);
        assertAll(
                ()->{
                    System.out.println("这是第一个验证");
                    assertEquals(9,addCount2);
                    },
                ()->assertEquals(9,addCount1)
        );

    }

    @Test
    public void test2(){
        ArrayList<Executable> executables = new ArrayList<>();

        int addCount1 = Calculator.sum(1,4);
        executables.add(()-> assertEquals(9,addCount1));

        int addCount2 = Calculator.sum(4,5);
        executables.add(()-> assertEquals(9,addCount2));

        assertAll("执行流",executables.stream());

    }
}
