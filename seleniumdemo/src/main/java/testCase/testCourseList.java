package testCase;

import Base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class testCourseList extends caseBase {
    public DriverBase curDriver;
    public testCourseList(){
        this.curDriver = InitDriver("chrome");
    }

    public List<String> listElement(){
        List<String> listString = new ArrayList<String>();
        WebElement eleNode = curDriver.findElement(By.className("moco-course-list"));
        List<WebElement> elementList = eleNode.findElements(By.className("course-card-container"));
        for(WebElement el:elementList){
            //listString.add(el.findElement(By.className("course-card-name")).getText());
           // listString.add(el.findElement(By.className("course-card-desc")).getText());
          listString.add(el.findElement(By.className("course-card-desc")).getText());
        }
        return listString;
    }


    @Test
    public void courseList(){
        curDriver.get("http://www.imooc.com/course/list");
        curDriver.supDriver.manage().window().maximize();
        try{
            new WebDriverWait(curDriver.supDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("redrain-closeBtn")));
            if(curDriver.findElement(By.className("redrain-closeBtn")).isDisplayed()){
                curDriver.findElement(By.className("redrain-closeBtn")).click();
            }
        }catch (TimeoutException t){
            System.out.println("Do not have such element*******************");
        }
        List<String> listEleString = this.listElement();
        for(int i=0;i<listEleString.size();i++){
            System.out.println("**************"+curDriver.supDriver.getCurrentUrl());
            int k = i+1;
            WebElement labels2 = curDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[2]/div[1]/div/div["+k+"]/a"));
            //WebElement aa = (WebElement) ((JavascriptExecutor) curDriver.supDriver).executeScript("arguments[0].setAttribute('target','_self')",labels2 );
            ((JavascriptExecutor) curDriver.supDriver).executeScript("arguments[0].setAttribute('target','_self')",labels2 );
            //aa.click();
            curDriver.findElement(By.xpath("//*[contains(text(),'"+listEleString.get(i)+"')]")).click();
            System.out.println("**************"+curDriver.supDriver.getCurrentUrl());
            curDriver.back();
            //curDriver.supDriver.close();
            System.out.println("**************"+curDriver.supDriver.getWindowHandle());
        }
    }
}
