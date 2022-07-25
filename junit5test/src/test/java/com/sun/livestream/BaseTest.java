package com.sun.livestream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BaseTest {

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
}
