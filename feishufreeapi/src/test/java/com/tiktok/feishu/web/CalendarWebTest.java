package com.tiktok.feishu.web;

import com.tiktok.feishu.model.Calendar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

class CalendarWebTest {

    private static CalendarWeb aut;

    @BeforeAll
    static void beforeAll() {
        aut = new CalendarWeb();
    }

    @Test
    void start() throws InterruptedException {
        aut.start();
        Calendar calendar = new Calendar();
        calendar.setSummary("demo web");
        List<Calendar> calendars = aut.add(calendar).getAll();
        List<String> summaryList = calendars.stream().map(Calendar::getSummary).collect(Collectors.toList());
        assertThat(summaryList, hasItem("demo web"));
    }

    @Test
    void add() {
    }
}