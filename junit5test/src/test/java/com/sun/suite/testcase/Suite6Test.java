package com.sun.suite.testcase;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.sun.suite.class1")
@ExcludeClassNamePatterns({
        "com.sun.suite.class1.*eTest"
})
public class Suite6Test {
}
