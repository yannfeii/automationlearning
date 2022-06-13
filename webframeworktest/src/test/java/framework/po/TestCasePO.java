package framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TestCasePO {

    public WebDriver driver;

    public String name;
    public List<HashMap<String,Object>> before;
    public List<HashMap<String,Object>> after;
    public List<HashMap<String,Object>> beforeAll;
    public List<HashMap<String,Object>> afterAll;

    public List<HashMap<String,Object>> steps;

    public void run(){

        AtomicReference<By> defaultBy = new AtomicReference<>();
        BasePagePO lastPage = null;

        steps.forEach(step->{
            step.entrySet().forEach(entry->{

                System.out.println("step"+entry);
                String action = entry.getKey().toLowerCase();
                Object value = entry.getValue();

                String[] keyArray = action.split("\\.");
                if(keyArray.length > 1) {
                    String poName = keyArray[0];
                    String poMethod = keyArray[1];
                    System.out.println(String.format("%s.%s %s", poName, poMethod, value));

                    if(poMethod.equals("new")){

                        BasePagePO currentPage = null;

//                        if(lastPage.get() == null){
//                            lastPage.set() =  StorePO.getInstance().setStorePo(
//                                    poName,
//                                    BasePagePO.load(
//                                            String.format("src/test/java/framework/po/%s.yaml", value),
//                                            null
//                                    ));
//                        } else {
//
//                            StorePO.getInstance().setStorePo(
//                                    poName,
//                                    BasePagePO.load(
//                                            String.format("src/test/java/framework/po/%s.yaml", value)));
//                        }
                    }else{
                        StorePO.getInstance().getStorePo(poName).runPOMethod(poMethod);

                    }

                }else {
                    System.out.println(String.format("%s %s", action, value));
                }


            });
        });
    }
}
