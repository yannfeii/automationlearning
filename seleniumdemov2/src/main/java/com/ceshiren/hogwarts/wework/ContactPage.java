package com.ceshiren.hogwarts.wework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ContactPage extends BasePage{

    public ContactPage(WebDriver driver){
        super(driver);
    }

    public ContactPage addMember(String account,String name, String phone){

        driver.findElement(By.linkText("Add members")).click();
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("acctid")).sendKeys(account);
        driver.findElement(By.name("mobile")).sendKeys(phone);
        driver.findElement(By.linkText("Save")).click();
        return this;
    }

    public ContactPage impContact(String impPath){

        return this;
    }

    public ContactPage expContact(String expPath){

        return this;
    }

    public List<String> getCurMemberList(){

        List<String> userList = new ArrayList<>();
        driver.findElements(By.cssSelector("#member_list td:nth-child(2) > span")).forEach(ele->{
            userList.add(ele.getText());
        });
       return userList;

    }
}
