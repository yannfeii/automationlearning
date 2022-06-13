package com.learning.springboot.common;

public class Constants {

    public static final String TOKEN = "token";
    public static final Integer DEL_FLAG_ZERO = 0;
    public static final Integer DEL_FLAG_ONE = 1;
    /**
     * 无效
     */
    public static final Integer STATUS_ZERO = 0;
    /**
     * 新建
     */
    public static final Integer STATUS_ONE = 1;
    /**
     * 执行中
     */
    public static final Integer STATUS_TWO = 2;
    /**
     * 已完成
     */
    public static final Integer STATUS_THREE = 3;

    public static final String md5Hex_sign = "Hogwarts_aitest-mini";

    /** 登陆token(nginx中默认header无视下划线) */
    public static final String LOGIN_TOKEN = "token";

    /**
     * 1 任务类型 普通测试任务
     */
    public final static Integer TASK_TYPE_ONE = 1;

    /**
     * 2 任务类型 一键执行测试任务
     */
    public final static Integer TASK_TYPE_TWO = 2;
}
