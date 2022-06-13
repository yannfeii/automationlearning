package com.tiktok.feishu.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface User {
    String token = null;
    String authorization = null;
    String appId = null;
    String appSecret = "yj6Sc8T4xfzHBzlRuOdoqhldtBdUdivi";

    public Calendar addCalendar();

    public void subscribe();

    public void deleteCalendar();

    public Event addEvent(String eventName);

    public void login();

    public Calendar getCurrentCalendar() ;

    public List<Calendar> getAllCalendar() throws JsonProcessingException;

    public Event addEvent(String summary, String startTime, String endTime) throws JsonProcessingException;
}
