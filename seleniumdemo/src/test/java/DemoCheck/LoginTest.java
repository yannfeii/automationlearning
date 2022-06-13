package DemoCheck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {
    Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }


    @Test
    void initalWeb() {
        login.initalWeb();
    }
}