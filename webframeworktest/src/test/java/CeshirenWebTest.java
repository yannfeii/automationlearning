import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.time.Duration;

public class CeshirenWebTest {

    static WebDriver webDriver;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\Document\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    }

    @AfterAll
    public static void afterAll(){
        webDriver.quit();
    }

    @Test
    public void search(){

        webDriver.get("https://ceshiren.com");
        webDriver.findElement(By.id("search-button")).click();

        String searchWord = "selenium";
        webDriver.findElement(By.id("search-term")).sendKeys(searchWord + Keys.ENTER);
        webDriver.findElement(By.id("search-term")).sendKeys(Keys.ENTER);
        String title = webDriver.findElements(By.cssSelector(".topic-title")).get(0).getText();
        assertThat(title,containsString(searchWord));

    }

}
