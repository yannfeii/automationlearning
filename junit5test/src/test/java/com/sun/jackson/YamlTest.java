package com.sun.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.entity.OrderList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class YamlTest {

    //直接声明数据结构
    @Test
    void yamlTest() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};

        List<HashMap<String,Object>> list = objectMapper.readValue(new File("src/test/resources/orderlist.yaml"),typeReference);
        System.out.println(list);
    }

    //实体类声明
    @Test
    void yamlEntityTest() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //objectMapper.registerModules(objectMapper.findModules());
        objectMapper.findAndRegisterModules();
        TypeReference<List<OrderList>> typeReference = new TypeReference<List<OrderList>>() {};
        List<OrderList> orderList = objectMapper.readValue(new File("src/test/resources/orderlist.yaml"),typeReference);

        System.out.println(orderList);
    }
}
