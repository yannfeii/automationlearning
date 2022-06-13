package DemoCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class seleniumAPI extends Login{

    public void getTagName(WebElement ele){
        System.out.println("This is tag name: "+ele.getTagName());
    }

    public void getAttribute(WebElement ele,String attrbute){
        System.out.println("This is attribute name: "+ele.getAttribute(attrbute));
    }

    public void simulateKeyBoard(WebElement ele){
        ele.sendKeys(Keys.ESCAPE);
    }

}
