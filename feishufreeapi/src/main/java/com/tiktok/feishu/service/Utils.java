package com.tiktok.feishu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static ObjectMapper mapper=new ObjectMapper();
//    public <T> T fromJson(String jsonText, JavaType javaType) throws JsonProcessingException {
//        return (T) mapper.readValue(jsonText, javaType.getRawClass());
//    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
