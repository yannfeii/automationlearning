package handle;

import Base.DriverBase;
import page.LoginPage;

public class loginPageHandle {
    public DriverBase driver;
    public LoginPage lp;

    public loginPageHandle(DriverBase driver){
        this.driver = driver;
        lp = new LoginPage(driver);
    }

    public void sendKeyUser(String username){
        lp.sendKeys(lp.getUserElement(),username);
    }

    public void sendKeyPassWord(String password){
        lp.sendKeys(lp.getPassWordElement(),password);
    }

    public void clickloginButton(){
        lp.click(lp.getLoginButtonElement());
    }

    public void clickAutologin(){
        lp.click(lp.getAutoLoginElement());
    }

    public boolean assertLoginPage(){
        return lp.assertElementIs(lp.getUserElement());
    }

}
