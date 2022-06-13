package page;

import Base.DriverBase;
import org.openqa.selenium.WebElement;
import util.getByLocator;

public class LoginPage extends BasePage {

    public LoginPage(DriverBase driver) {
        super(driver);
    }

    public WebElement getUserElement(){ return element(getByLocator.getByLocator("User")); }

    public WebElement getPassWordElement(){
        return element(getByLocator.getByLocator("PassWord"));
    }

    public WebElement getLoginButtonElement(){
        return element(getByLocator.getByLocator("LoginBtn"));
    }

    public WebElement getAutoLoginElement(){
        return element(getByLocator.getByLocator("autoSign"));
    }
}
