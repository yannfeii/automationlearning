package com.ceshiren.hogwarts.ceshiren;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchPage {

    private final ChromeDriver driver;
    public SearchPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public String getFirstTitle() {

        String title = driver.findElement(By.cssSelector(".topic-title")).getText();
        return title;
    }
}
