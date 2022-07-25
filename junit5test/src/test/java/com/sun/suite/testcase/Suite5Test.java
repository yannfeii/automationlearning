package com.sun.suite.testcase;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.sun.suite.class1")
@IncludeClassNamePatterns({
        "com.sun.suite.class1.*Test"
})
public class Suite5Test {
}
