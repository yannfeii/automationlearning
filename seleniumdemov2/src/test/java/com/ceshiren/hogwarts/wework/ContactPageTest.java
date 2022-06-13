package com.ceshiren.hogwarts.wework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

class ContactPageTest{

    private static MainPage mainPage;
    private static ContactPage contactPage;

    @BeforeAll
    static void beforeAll() throws IOException {
        mainPage = new WeWork().defaultPage();
        contactPage = mainPage.toContactPage();
    }

    @Test
    void addMember() {
        List<String> userList = contactPage.addMember("2022042801","YF","18200120606").getCurMemberList();
        assertThat(userList,hasItem("2022042801"));
    }

    @Test
    void impContact() {
    }
}