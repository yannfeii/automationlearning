package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverBase {
    public WebDriver supDriver;
    public DriverBase(String browser){
        SelectDriver selectDriver = new SelectDriver();
        this.supDriver = selectDriver.DriverName(browser);
    }
    public void stop(){
        System.out.println("stop webdriver");
        supDriver.close();
    }
    public WebElement findElement(By by){
        WebElement element = supDriver.findElement(by);
        return element;
    }

    public void get(String url){
        supDriver.get(url);
    }

    public void back(){
        supDriver.navigate().back();
    }


}
