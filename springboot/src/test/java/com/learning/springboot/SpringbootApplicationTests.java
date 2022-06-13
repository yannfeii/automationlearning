package com.learning.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    //hostname -I
    //telnet 192.168.19.128 3306
    public void jdbCall() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动类
        String url="jdbc:mysql://192.168.19.128:3306/test_data";
        String username="root";
        String password="mysql2022";
        Connection conn= DriverManager.getConnection(url,username,password);//用参数得到连接对象
        System.out.println("连接成功！");
        System.out.println(conn);
    }

}
