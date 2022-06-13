package com.tiktok.feishu.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.ArrayList;

import java.util.List;

import static com.tiktok.feishu.service.Utils.mapper;


public class Calendar {
    String summary = null;
    String summary_alias = null;
    String type = null;
    String role = null;
    String permissions = null;
    String description = null;
    String calendar_id = null;
    Integer color = null;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;

    }

    public String getSummary_alias() {
        return summary_alias;
    }

    public void setSummary_alias(String summary_alias) {
        this.summary_alias = summary_alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(String calendar_id) {
        this.calendar_id = calendar_id;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Event addEvent(String summary, String startTime, String endTime, String token) throws JsonProcessingException {
        return null;
    }

    public void deleteEvent(Event event) {

    }

    public List<Event> getEvents() {
        return new ArrayList<Event>();
    }


    public static Calendar fromJson(String text) throws JsonProcessingException {
        return mapper.readValue(text, Calendar.class);
    }

    public static List<Calendar> fromJsonList(String text) throws JsonProcessingException {
        return mapper.readValue(text, new TypeReference<List<Calendar>>() {
        });
    }

    public List<Calendar> getAll() {
        return new ArrayList<>();
    }

    public Calendar add(Calendar calendar) {
        return null;
    }

    public Calendar start(){
        return null;
    }
}
