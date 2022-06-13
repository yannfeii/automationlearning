package com.ceshiren.wechart.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WechatTest {

    @Test
    public void getWechatCookies() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        Thread.sleep(10000);
        Set<Cookie> cookies = driver.manage().getCookies();
        //yaml格式的序列化和反序列化
        ObjectMapper mapper =new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);

    }

    @Test
    public void loginWechatWithCookies() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        //yaml格式的序列化和反序列化
        ObjectMapper mapper =new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String,Object>> cookies = mapper.readValue(this.getClass().getClassLoader().getResource("cookies.yaml"), typeReference);

        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();

    }
}
