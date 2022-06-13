package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectDriver {
    public WebDriver DriverName(String browser){
        if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","D:\\SelfStudy\\Document\\chromedriver.exe");
            return new ChromeDriver();

        }else {
            return new ChromeDriver();
        }
    }
}
