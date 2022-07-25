package com.sun.basic;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedExampleTest {

    @Nested
    class AuthorityManage{
        @Nested
        class Manger{
            @Test
            void addUser(){
                System.out.println("新增用户");
            }
            @Test
            void delUser(){
                System.out.println("删除用户");
            }
        }
        @Test
        void employeeManger(){
            System.out.println("员工管理");
        }
    }
}
