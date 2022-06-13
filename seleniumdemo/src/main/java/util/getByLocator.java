package util;

import org.openqa.selenium.By;

public class getByLocator {

    public static By getByLocator(String eleKey){
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
}
