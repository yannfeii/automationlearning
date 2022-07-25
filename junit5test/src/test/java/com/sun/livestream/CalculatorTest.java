package com.sun.livestream;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * 根据需求编写对应的测试用例：加法，减法，string拼接
 * 第一步：打开计算器
 * 在每个测试方法之前生成运行的唯一ID标识
 * 在每个测试方法之前log打印：开始进行计算
 * 在测试方法得到结果后log打印：计算结果:result
 * 在每个测试方法之后进行销毁ID操作
 * 在调用完所有测试用例后执行关闭计算器app操作
 * 注意:warning:：
 * 每个测试用例都要添加断言，验证结果
 * 灵活使用测试装置
 * 边界值要进行验证
 */
class CalculatorTest {

    static final Logger logger = getLogger(lookup().lookupClass());
    static Calculator calculator;

    @BeforeAll
    static void setup(){
        calculator = new Calculator("Test calculator");
    }

    @BeforeEach
    void afterEach(){
        calculator.initId();
        logger.info("开始进行计算");
    }
    @AfterEach
    void destroyUUID(){
        calculator.destroyId();
    }

    @AfterAll
    static void afterAll(){
        calculator.close();
    }

    @Test
    void sum() {
        Integer result = calculator.sum(10,10);
        logger.info("计算结果:result"+result);
        //assertEquals(20,result);
        assertThat("",result,is(equalTo(200)));

    }

    @Test
    void subtract() {
        Integer result = calculator.subtract(99,1);
        logger.info("计算结果:result"+result);
        assertEquals(98,result);
    }

    @Test
    void testSubtract() {
    }

    @Test
    void average() {
    }

    @Test
    void concatStr() {
    }
}