package page;

import Base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    public DriverBase driver;
    public BasePage(DriverBase driver){
        this.driver = driver;
    }
    public WebElement element(By by){
        WebElement element = driver.findElement(by);
        return element;
    }
    public void click(WebElement element){
        if(element!=null){
            element.click();
        }else{
            System.out.println("Element does not find!!!");
        }
    }

    public void sendKeys(WebElement element, String value){
        if(element!=null){
            element.sendKeys(value);
        }else{
            System.out.println("Elemnet doet not find, unable to send value");
        }
    }

    public boolean assertElementIs(WebElement element){
        return element.isDisplayed();
    }

    public String getText(WebElement element){
        return element.getText();
    }

}

