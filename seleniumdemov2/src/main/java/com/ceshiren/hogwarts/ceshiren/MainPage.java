package com.ceshiren.hogwarts.ceshiren;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    private ChromeDriver driver;
    private static final By search_term = By.id("search-term");
    private static final By search_button = By.id("search-button");

    public MainPage start(){

        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ceshiren.com");
        return this;

    }

    public SearchPage search(String keyword) {

        driver.findElement(search_button).click();
        driver.findElement(search_term).sendKeys(keyword+ Keys.ENTER);

        return new SearchPage(driver);
    }
}
