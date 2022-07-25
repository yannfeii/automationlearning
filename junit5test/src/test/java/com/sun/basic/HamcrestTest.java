package com.sun.basic;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class HamcrestTest {

    @Test
    void UsingIsForMatch(){
        String testString = "hamcrest core Is match";

        assertThat(testString, Matchers.is("hamcrest core Is match"));
        assertThat(testString, Matchers.is(Matchers.equalTo("hamcrest core Is match")));
    }

    @Test
    void UsingEqualToForMatch(){
        //T 泛型
        String actualString = "equalTo match";
        List<String> actualList = Arrays.asList("equalTo", "match");
        Object original = "100";
        List<String> stringList = Arrays.asList("equalTo", "match");

        assertThat(actualString, Matchers.is(Matchers.equalTo("equalTo match")));
        assertThat(actualList, Matchers.is(Matchers.equalTo(stringList)));
        assertThat(original, Matchers.equalToObject("100"));
    }



    @Test
    void UsingNotForMatch(){
        String testString = "hamcrest not match";

        assertThat(testString, Matchers.not("hamcrest other match"));
        assertThat(testString, Matchers.is(Matchers.not(Matchers.equalTo("hamcrest other match"))));
        assertThat(testString, Matchers.is(Matchers.not(Matchers.instanceOf(Integer.class))));

    }

    @Test
    void UsingHasItemForMatch(){
        List<String> list = Arrays.asList("java", "hamcrest", "junit5");

        assertThat(list, Matchers.hasItem("java"));
        assertThat(list, Matchers.hasItem(Matchers.isA(String.class)));

        assertThat(list, Matchers.hasItems("java", "junit5"));
        assertThat(list, Matchers.hasItems(Matchers.isA(String.class), Matchers.endsWith("est"), Matchers.containsString("j")));
    }

    @Test
    void UsingAllOfForMatch(){
        String testString = "Achilles is powerful";
        assertThat(testString, Matchers.allOf(
                Matchers.startsWith("Achi"), Matchers.endsWith("ul"), Matchers.containsString("Achilles")));

    }

    @Test
    void UsingAnyOfForMatch(){
        String testString2 = "Hector killed Achilles";
        assertThat(testString2, Matchers.anyOf(Matchers.startsWith("Hec"), Matchers.containsString("baeldung")));
    }

    @Test
    void UsingBothForMatch(){
        String testString = "daenerys targaryen";
        assertThat(testString, Matchers.both(Matchers.startsWith("daene")).and(Matchers.containsString("yen")));
        assertThat(testString, Matchers.either(Matchers.startsWith("tar")).or(Matchers.containsString("targaryen")));

    }
}
