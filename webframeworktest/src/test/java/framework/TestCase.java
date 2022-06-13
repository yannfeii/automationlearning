package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TestCase {

    public WebDriver driver;

    public String name;
    public List<HashMap<String,Object>> before;
    public List<HashMap<String,Object>> after;
    public List<HashMap<String,Object>> beforeAll;
    public List<HashMap<String,Object>> afterAll;

    public List<HashMap<String,Object>> steps;

    public void run(){

        AtomicReference<By> defaultBy = new AtomicReference<>();
        steps.forEach(step->{
            step.entrySet().forEach(entry->{

                System.out.println("step"+entry);
                String action = entry.getKey().toLowerCase();
                Object value = entry.getValue();

                if(action.equals("get")) {
                    driver.get((String) value);
                }else if(action.equals("find")){
                    ArrayList<String> values = (ArrayList<String>) value;
                    String locatorBy = values.get(0);
                    String locatorValue = values.get(1);

                    if(locatorBy.equals("id")){
                        defaultBy.set(By.id(locatorValue));
                    }else if(locatorBy.equals("css")){
                        defaultBy.set(By.cssSelector(locatorValue));
                    }
                }else if(action.equals("click")){
                    driver.findElement(defaultBy.get()).click();
                }else if(action.equals("sendkeys")){
                    String keys = (String) value;
                    driver.findElement(defaultBy.get()).sendKeys(keys);
                }else if(action.equals("chrome")){
                    System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\Document\\chromedriver.exe");
                    driver = new ChromeDriver();
                }else if (action.equals("implicitlyWait")){
                    Integer seconds = (Integer) value;
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
                }
            });
        });
    }
}
