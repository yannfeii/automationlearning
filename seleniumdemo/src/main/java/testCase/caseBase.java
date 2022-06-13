package testCase;

import Base.DriverBase;

public class caseBase {
    public DriverBase InitDriver(String browser){
       return new DriverBase(browser);
    }

}
