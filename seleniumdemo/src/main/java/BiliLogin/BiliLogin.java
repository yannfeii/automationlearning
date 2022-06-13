package BiliLogin;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;


public class BiliLogin {

    public void initWeb() throws InterruptedException {
        Utils biliUtil = new Utils();
        WebDriver driver = biliUtil.initDriver();
        //driver.get("https://passport.bilibili.com/login");
        driver.get("https://www.geetest.com/demo/slide-bind.html");
        driver.manage().window().maximize();
        //driver.findElement(By.id("login-username")).sendKeys("psychologlic@163.com");
        //driver.findElement(By.id("login-passwd")).sendKeys("_Tangyanfei!");
        boolean letGo = false;
        letGo = biliUtil.waitEle("id","btn");
        if(letGo) {

            driver.findElement(By.id("btn")).click();

            System.out.println("*************We can process*******************");
        }
        biliUtil.loginNext();
        //ChromeDriverService service = new ChromeDriverService.Builder().usingPort(8080).build();
    }



    public static void main(String[] args) throws InterruptedException {
        BiliLogin biLi = new BiliLogin();
        biLi.initWeb();
    }
}
