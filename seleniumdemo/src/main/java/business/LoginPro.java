package business;

import Base.DriverBase;
import handle.loginPageHandle;


public class LoginPro {
    public loginPageHandle lph;

    public LoginPro(DriverBase driver){
        lph = new loginPageHandle(driver);
    }

    public void login(String username,String password){
        if(lph.assertLoginPage()){
            lph.sendKeyUser(username);
            lph.sendKeyPassWord(password);
            lph.clickAutologin();
            lph.clickloginButton();
        }else {
            System.out.println("**************page does not exist************");
        }
    }
}
