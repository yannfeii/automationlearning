package com.tiktok.feishu;

import com.tiktok.feishu.model.Calendar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

class CalendarTest {

    private static Calendar aut;

    @BeforeAll
    static void beforeAll() throws Exception {
        aut = CalendarFactory.createCalendar(System.getenv("automation"));
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