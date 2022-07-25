package com.sun.suite.testcase;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages({
        "com.sun.suite.class1",
        "com.sun.suite.class2"
})
@SuiteDisplayName("执行多个测试包的suite套件")
public class Suite2Test {
}
