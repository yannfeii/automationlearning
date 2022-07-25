package com.sun.suite.testcase;

import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
        "com.sun.suite"
})
@IncludePackages("com.sun.suite.class1")
public class Suite3Test {
}
