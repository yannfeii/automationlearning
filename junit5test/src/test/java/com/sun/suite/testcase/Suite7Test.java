package com.sun.suite.testcase;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.sun.suite.class2")
@IncludeTags("SuiteTag")
public class Suite7Test {
}
