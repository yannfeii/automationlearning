package com.tiktok.feishu.web;

import com.tiktok.feishu.model.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CalendarWeb extends Calendar {

    private ChromeDriver driver;

    @Override
    public CalendarWeb start() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://ev5od8sfi3.feishu.cn/calendar/week");
        return this;
    }

    public Calendar add(Calendar calendar) {
        driver.findElement(By.cssSelector(".sidebar-more-trigger")).click();
        driver.findElement(By.cssSelector(".sidebar-more-popup-item .icon-add")).click();
        driver.findElement(By.cssSelector(".calendar-title input")).sendKeys(calendar.getSummary());
        driver.findElement(By.cssSelector(".confirm-wrapper button")).click();

        return this;

    }

    @Override
    public List<Calendar> getAll() {
        List<Calendar> calendars = new ArrayList<>();

        for (WebElement element : driver.findElements(By.cssSelector(".calendars .calendar-item"))) {
            Calendar calendar = new Calendar();
            calendar.setSummary(element.findElement(By.cssSelector(".summary")).getText());
            calendars.add(calendar);
        }

        return calendars;
    }
}
