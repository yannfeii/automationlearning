
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.Element;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Demo {
    WebDriver driver;
    String curWindow;
    public void initWeb() {
        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\SeleniumDemo\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.imooc.com/");
        driver.manage().window().maximize();
        // mouse action
//        WebElement login = driver.findElement(By.id("js-signin-btn"));
//        Actions action =new Actions(driver);
//        //action.click(login).perform();
//        //action.doubleClick(login).perform();
//        //action.contextClick(login).perform();
////        WebElement move = driver.findElement(By.className("group"));
////        action.moveToElement(move).perform();


        driver.get("https://www.imooc.com/user/newlogin");
//        //driver.findElement(By.className("imv2-weibo")).click();
//

        driver.findElement(By.name("email")).sendKeys("18328362676");
        driver.findElement(By.name("password")).sendKeys("mk18328362676");
        driver.findElement(By.className("moco-btn")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void radioBox(){
        driver.get("https://www.imooc.com/user/setprofile");
        driver.findElement(By.className("pull-right")).click();
        //driver.findElement(By.xpath("//*[@id=\"profile\"]/div[4]/div/label[1]")).click();
        List<WebElement> inputElements = driver.findElements(By.xpath("//*[@id=\"profile\"]/div[4]/div/label//input"));
        for (WebElement radio:inputElements){
            boolean flag = radio.isSelected();

            if(!flag){
                radio.click();
            }else{
                System.out.println(radio+" is selected");
            }

        }
        //driver.findElement(By.id("profile-submit")).click();

        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public void checkBox(){
        WebElement checkStatus = driver.findElement(By.id("auto-signin"));
        System.out.println(checkStatus.isSelected()+ " is selected");
        System.out.println(checkStatus.isEnabled()+ " is enabled");
        //checkStatus.clear(); //this atttibute cannot be use for checkbox
        checkStatus.click();
        System.out.println(checkStatus.isSelected()+ " is selected");
        System.out.println(checkStatus.isEnabled()+ " is enabled");
    }
    public void webForm(){
        driver.findElement(By.id("signup-form")).submit();
        //selenium submit form is not available for web, can follow interface test
    }
    public void uploadFile(){
        driver.get("https://www.imooc.com/user/setbindsns");
        String jsString = "document.getElementsByClassName('update-avator')[0].style.bottom ='0'";
        JavascriptExecutor jsEXE = (JavascriptExecutor) driver;
        jsEXE.executeScript(jsString);
        driver.findElement(By.className("js-avator-link")).click();
        driver.findElement(By.id("upload")).sendKeys("C:\\Users\\yanfeit\\Downloads\\Gary.jpg");

    }

    public void dropDownList(){
        driver.get("https://www.imooc.com/user/setprofile");
        driver.findElement(By.className("pull-right")).click();
        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        WebElement formElement = driver.findElement(By.id("profile"));
        WebElement province = formElement.findElement(By.id("province-select"));
        Select provinceList = new Select(province);
       // provinceList.selectByIndex(2);
       // provinceList.selectByValue("1");
        // provinceList.selectByVisibleText("北京");
        provinceList.selectByValue("1");
        System.out.println("isMultiple "+provinceList.isMultiple());
        //provinceList.deselectByValue("1");//You may only deselect options of a multi-select
        List<WebElement> curSelect = provinceList.getAllSelectedOptions();
        for(WebElement option:curSelect){
            System.out.println(option.getText());
        }
        System.out.println(provinceList.getFirstSelectedOption().getText());
    }

    public void iFrame(){
        driver.get("https://www.imooc.com/article/publish#");
        WebElement iframeEle = driver.findElement(By.id("ueditor_0"));
        driver.switchTo().frame(iframeEle);
        driver.findElement(By.tagName("body")).sendKeys("this is for test");

    }
    public void windowsHandle(){
        Set<String> whandles = driver.getWindowHandles();

        driver.findElement(By.className("imooc")).click();
        Actions action =new Actions(driver);
        WebElement move = driver.findElement(By.className("group"));
        action.moveToElement(move).perform();
        driver.findElement(By.linkText("微服务")).click();
        curWindow = driver.getWindowHandle();
        for(String w:whandles){
            if(w.equals(curWindow)){
                continue;
            }
            driver.switchTo().window(w);
           // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待

        }
    }
    public void waitWay(){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(10));
        //waitTime.until(ExpectedConditions.presenceOfElementLocated(By.id("su")));
        driver.findElement(By.linkText("升级")).click();
    }
    public static void main(String[] args) {
        Demo as = new Demo();
        as.initWeb();
       // as.radioBox();
       // as.checkBox();
        //as.uploadFile();
        //as.dropDownList();
        //as.iFrame();
        as.windowsHandle();
        as.waitWay();

    }
}

