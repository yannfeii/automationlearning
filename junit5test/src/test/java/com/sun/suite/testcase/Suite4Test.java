package com.sun.suite.testcase;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.sun.suite")
@ExcludePackages({
        "com.sun.suite.class2",
        "com.sun.suite.testcase"
})
public class Suite4Test {
}
