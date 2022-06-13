package testCase;

import Base.DriverBase;
import business.LoginPro;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class login extends caseBase {
    public DriverBase driver;
    public LoginPro loginPro;
    public Logger logger = Logger.getLogger(login.class);
    public login(){
        this.driver = InitDriver("chrome");
        loginPro = new LoginPro(driver);
    }
    @Test
    public void getLoginHome(){
        driver.get("https://www.imooc.com/");
        driver.supDriver.manage().window().maximize();
        try{
            new WebDriverWait(driver.supDriver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.className("redrain-closeBtn")));
            if((driver.findElement(By.className("redrain-closeBtn")).isDisplayed())){
                driver.findElement(By.className("redrain-closeBtn")).click();
            }
        }catch (TimeoutException t){
            System.out.println("Do not have such element*******************");
        }


        driver.findElement(By.id("js-signin-btn")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"getLoginHome"})
    public void testLogin(){
        //logger.debug("this is first time log4j~~~~~~~~~~~~");
        new WebDriverWait(driver.supDriver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.id("signin")));
        loginPro.login("18328362676","mk18328362676");
    }

    public String getCourseText(WebElement element){
        return element.getText();
    }
    @Test(dependsOnMethods = {"testLogin","getLoginHome"})
    public void buyCourse(){
        //logger.debug("this is first time log4j~~~~~~~~~~~~");
        driver.get("https://coding.imooc.com/class/419.html");
        String courseDetail = driver.findElement(By.className("path")).findElement(By.tagName("span")).getText();
        driver.findElement(By.id("buy-trigger")).click();
        driver.findElement(By.className("js-pay-submit")).click();
        //driver.findElement(By.linkText("提交订单")).click();
        String orderText = driver.findElement(By.className("order")).getText();
        if(orderText.length()>0){
            String orderString = driver.findElement(By.className("left")).findElement(By.tagName("dt")).getText();
            Assert.assertEquals(courseDetail,orderString,"The course is not the same");
        }


    }


}
