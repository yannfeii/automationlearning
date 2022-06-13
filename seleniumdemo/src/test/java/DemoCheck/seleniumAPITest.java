package DemoCheck;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

class seleniumAPITest {
    seleniumAPI seleniumAPIcheck;
    WebElement initEle;

    @BeforeEach
    void setUp() {
        seleniumAPIcheck = new seleniumAPI();
    }

    @Test
    void testWait(){
    }
    @Test
    void getTagName() {
        seleniumAPIcheck.getTagName(initEle);
    }

    @Test
    void getAttribute() {
    }

    @Test
    void simulateKeyBoard() {
        seleniumAPIcheck.simulateKeyBoard(initEle);
    }
}