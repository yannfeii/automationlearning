package com.ceshiren.hogwarts.wework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class WeWork {

    private ChromeDriver driver;

    public void start() throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\chromedriver.exe");
        driver = new ChromeDriver();
        loadCookie();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    void loadCookie() throws IOException {

        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        //yaml格式的序列化和反序列化
        ObjectMapper mapper =new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String,Object>> cookies = mapper.readValue(this.getClass().getClassLoader().getResource("\\cookies.yaml"), typeReference);

        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();
    }

    MainPage defaultPage() throws IOException {
        start();
        return new MainPage(driver);
    }
}
