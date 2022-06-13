package BaseLearn;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver","D:\\SelfStudy\\SeleniumDemo\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
        driver.manage().window().fullscreen();
        //driver.manage().window().setSize(new Dimension(400,800));
        //driver.manage().window().maximize();
        System.out.println(driver.findElement(By.linkText("百度热榜")));


    }

}
