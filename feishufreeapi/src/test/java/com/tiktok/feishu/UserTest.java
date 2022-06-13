package com.tiktok.feishu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tiktok.feishu.model.Event;
import com.tiktok.feishu.service.CalendarService;
import com.tiktok.feishu.service.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCalendar() {
    }

    @Test
    void subscribe() {
    }

    @Test
    void deleteCalendar() {
    }



    @Test
    void addEvent() throws JsonProcessingException {
        User user = new User();
        user.login();
        CalendarService calendar = user.getCurrentCalendar();
        Event event=user.addEvent("demo event", "2022-05-15", "2022-05-16");
        assertEquals(event.getSummary(), "demo event");
    }

    @Test
    void addEventFail() throws JsonProcessingException {
        User user = new User();
        user.login();
        Event event = user.addEvent("demo event");
        assertFalse(user.getCurrentCalendar().getEvents().contains("demo event"));
    }
}