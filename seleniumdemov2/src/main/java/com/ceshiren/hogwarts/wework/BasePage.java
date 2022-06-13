package com.ceshiren.hogwarts.wework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    public final WebDriver driver;

    public BasePage(WebDriver driver){

        this.driver = driver;
    }

    public WebElement find(By by){

        //todo:当出现异常时，解决异常回归到当前步骤继续执行
        //todo:递归设计，对递归深度做限制

        WebElement element = null;

        try{
            element = driver.findElement(by);
        }catch (Exception e){
            e.printStackTrace();
            element = this.find(by);
        }

        return element;
    }
}
