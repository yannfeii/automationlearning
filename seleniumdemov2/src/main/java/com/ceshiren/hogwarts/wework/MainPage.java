package com.ceshiren.hogwarts.wework;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){
        super(driver);
    }

    List<String> getMessage(){
        //todo:
        List<String> messages = new ArrayList<>();
       messages.add("1");
       return messages;
    }

    ContactPage toContactPage(){
        return new ContactPage(driver);
    }
}
