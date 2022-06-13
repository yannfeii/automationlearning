package com.tiktok.feishu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tiktok.feishu.model.Calendar;
import com.tiktok.feishu.model.Event;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class User {
    private String token;
    private String authorization;
    private String appId = "cli_a202161a90f8100e";
    private String appSecret = "yj6Sc8T4xfzHBzlRuOdoqhldtBdUdivi";

    public CalendarService addCalendar() {
        return null;

    }

    public void subscribe() {

    }

    public void deleteCalendar() {

    }

    public Event addEvent(String eventName) {
        return new Event();
    }

    public void login() {
        Response response = given()
                .param("app_id", appId).param("app_secret", appSecret)
                .when()
                .post("https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal")
                .andReturn();
        token = response.path("tenant_access_token");
        authorization = "Bearer " + token;
        System.out.println(token);

    }

    public CalendarService getCurrentCalendar() throws JsonProcessingException {
        return CalendarService.getDefault(authorization);
    }

    public List<Calendar> getAllCalendar() throws JsonProcessingException {
        return CalendarService.getAll(authorization);
    }

    public Event addEvent(String summary, String startTime, String endTime) throws JsonProcessingException {
        return CalendarService.getDefault(authorization).addEvent(summary, startTime, endTime, this.authorization);
    }
}
