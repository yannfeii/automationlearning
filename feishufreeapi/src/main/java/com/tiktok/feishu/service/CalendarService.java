package com.tiktok.feishu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.tiktok.feishu.model.Calendar;
import com.tiktok.feishu.model.Event;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tiktok.feishu.service.Utils.mapper;
import static io.restassured.RestAssured.given;

public class CalendarService extends Calendar {

    public Event addEvent(String summary, String startTime, String endTime, String token) throws JsonProcessingException {

        HashMap<String , Object> data=new HashMap<>();
        data.put("summary", summary);

        HashMap<String, Object> startTimeMap = new HashMap<String, Object>();
        startTimeMap.put("date", startTime);

        HashMap<String, Object> endTimeMap = new HashMap<String, Object>();
        endTimeMap.put("date", endTime);
//        endTimeMap.put("timestamp", "1602504000");

        data.put("start_time", startTimeMap);
        data.put("end_time", endTimeMap);

        Response res = given().log().all()
                .header("Authorization", token)
                .pathParam("calendar_id", this.getCalendar_id())
                .contentType(ContentType.JSON)
                .body(data)
//                .when().post("http://127.0.0.1:7777/{calendar_id}/")
                .when().post("https://open.feishu.cn/open-apis/calendar/v4/calendars/{calendar_id}/events")
                .andReturn();

        System.out.println(res.asPrettyString());
        String eventText = Utils.toJson(res.path("data.event"));
        return mapper.readValue(eventText, Event.class);
    }

    public void deleteEvent(Event event) {

    }

    public List<Event> getEvents() {
        return new ArrayList<Event>();
    }

    public static CalendarService getDefault(String token) throws JsonProcessingException {
        Object response = given().header("Authorization", token)
                .when().post("https://open.feishu.cn/open-apis/calendar/v4/calendars/primary")
                .path("data.calendars[0].calendar");
        System.out.println(response);
        return fromJson(Utils.toJson(response));
    }

    public static List<Calendar> getAll(String token) throws JsonProcessingException {
        Object res = given().header("Authorization", token)
                .when().get("https://open.feishu.cn/open-apis/calendar/v4/calendars")
                .path("data.calendar_list");
        System.out.println(res.getClass());
        String jsonText=Utils.toJson(res);
        System.out.println(jsonText);
        return fromJsonList(jsonText);
    }

    public static CalendarService fromJson(String text) throws JsonProcessingException {
        return mapper.readValue(text, CalendarService.class);
    }

    public static List<Calendar> fromJsonList(String text) throws JsonProcessingException {
        return mapper.readValue(text, new TypeReference<List<Calendar>>() {
        });
    }

    @Override
    public Calendar add(Calendar calendar) {
        return super.add(calendar);
    }
}
