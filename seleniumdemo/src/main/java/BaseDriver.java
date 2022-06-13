//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class BaseDriver {
    WebDriver driver;
    public BaseDriver(){
        System.setProperty("webdriver.chrome.driver","D:\\SelfStudy\\SeleniumDemo\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void takeScreenShot(){
        long curDate = System.currentTimeMillis();
        String curPath = System.getProperty("user.dir");
        String screenPath = String.valueOf(curDate)+ ".png";
        String screenSavedPath = curPath+"/"+screenPath;
        File screen =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(screen,new File(screenSavedPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//       }

    }

}
