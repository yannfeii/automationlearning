package DemoCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    static final WebDriver CHROME_DRIVER;

    static{
        System.setProperty("webdriver.chrome.driver","D:\\SelfStudy\\SeleniumDemo\\chromedriver.exe");
        CHROME_DRIVER = new ChromeDriver();
    }
    public void initalWeb(){
        CHROME_DRIVER.get("https://pan.baidu.com/");
        CHROME_DRIVER.manage().window().maximize();
        CHROME_DRIVER.manage().window().fullscreen();
        WebDriverWait driverWait=new WebDriverWait(CHROME_DRIVER,6);
        WebElement baiduYun=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TANGRAM__PSP_4__userName")));
        baiduYun.sendKeys("psychologlic@163.com");
        CHROME_DRIVER.findElement(By.id("TANGRAM__PSP_4__password")).sendKeys("_Tangyanfei123");
        CHROME_DRIVER.findElement(By.id("TANGRAM__PSP_4__submit")).submit();
    }
}
