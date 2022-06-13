import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.Listeners;
import util.PropertiesUtil;

@Listeners({TestingListenerScreenShot.class})
public class login extends BaseDriver{
    public void InitDriver(){
        System.setProperty("webdriver.chrome.driver","D:\\SelfStudy\\SeleniumDemo\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.imooc.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("js-signin-btn")).click();
    }
    public WebElement findEleMode(By by){
        WebElement eleMode = driver.findElement(by);
        return eleMode;
    }
    public By InputElementType(String eleKey){
        PropertiesUtil properties = new PropertiesUtil("element.properties");
        String propResult = properties.getProperty(eleKey);
        String locatorType = propResult.split(">")[0];
        String locatorValue = propResult.split(">")[1];
        if(locatorType.equals("id")){
            return By.id(locatorValue);
        }else if(locatorType.equals("className")){
            return By.className(locatorValue);
        }else if(locatorType.equals("name")) {
            return By.name(locatorValue);
        }else {
            return By.xpath(locatorValue);
        }
    }

    public void LoginScrip(String LoginUser,String LoginPassWord){
        this.InitDriver();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        By test =  this.InputElementType("User");
        WebElement user = this.findEleMode(this.InputElementType("User"));
        user.isDisplayed();
        WebElement PassWord = this.findEleMode(this.InputElementType("PassWord"));
        PassWord.isDisplayed();
        WebElement LoginBtn = this.findEleMode(this.InputElementType("LoginBtn"));
        LoginBtn.isDisplayed();
        user.sendKeys(LoginUser);
        PassWord.sendKeys(LoginPassWord);
        LoginBtn.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("header-avator")));
        WebElement header =  this.findEleMode(this.InputElementType("header"));
        header.isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(header).perform();
        String userInfo = this.findEleMode(this.InputElementType("userInfo")).getText();
        if(userInfo.equals("呙志刚1101494712")) {
            System.out.println("Login successful！！！！");
           // this.takeScreenShot();
        }else{
            System.out.println("Login failed！！！！");
        }
        driver.close();
    }


    public static void main(String[] args) throws InterruptedException {
        login logInFlow = new login();
        HashMap userList = new HashMap();
        userList.put("18328362676","mk18328362676");
        userList.put("18328362676","18328362676");
        Iterator us = userList.entrySet().iterator();
        while(us.hasNext()){
            Map.Entry entry = (Map.Entry) us.next();
            String username = entry.getKey().toString();
            String password = entry.getValue().toString();
            logInFlow.LoginScrip(username,password);
        }
    }
}
