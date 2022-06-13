package framework.po;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BasePagePO {

    public String name;
    public HashMap<String, List<HashMap<String, Object>>> methods;

    WebDriver driver;

    Integer retryTimes = 3;

    public BasePagePO(WebDriver driver){
        this.driver = driver;
    }

    public static BasePagePO load(String name, WebDriver webDriver){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        BasePagePO page = null;
        try {
            page = mapper.readValue(new File(name), BasePagePO.class);
            page.driver = webDriver;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public void runPOMethod(String methodName){

    }

    public boolean click(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            e.printStackTrace();
            retryTimes +=1;
            if(retryTimes < 4){
                //this.handleAlert();
                return click(by);
            }else {
                retryTimes = 0;
                return false;
            }
        }
        return true;
    }

    public void clickUntil(By by, By next){
        //todo:用来解决前几次点击不生效，后面点击生效的过程，使用显示等待
    }

    public void get() {
    }

    public void set() {
    }
}
