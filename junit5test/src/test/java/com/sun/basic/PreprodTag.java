package com.sun.basic;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义标签
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("Preprod")
@Test
public @interface PreprodTag {
}
