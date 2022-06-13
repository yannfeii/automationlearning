package com.ceshiren.hogwarts.ceshiren.search;

import com.ceshiren.hogwarts.ceshiren.MainPage;
import com.ceshiren.hogwarts.ceshiren.SearchPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ceshirenPOTest {

    @Test
    void search(){
        MainPage mainPage = new MainPage();
        SearchPage searchResult = mainPage.search("selenium");
        String title = searchResult.getFirstTitle();
        assertThat(title,containsString("selenium"));

    }

    @Test
    void searchMaul(){
        MainPage mainPage = new MainPage();
        String title = mainPage.start().search("selenium").getFirstTitle();
        assertThat(title,containsString("selenium"));

    }
}
