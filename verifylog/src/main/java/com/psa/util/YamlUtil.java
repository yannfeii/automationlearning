package com.psa.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YamlUtil {

    public static Map<String,List<String>> readYamlFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        TypeReference<Map<String,List<String>>> typeReference = new TypeReference<Map<String,List<String>>>(){};
        Map<String,List<String>> transIdList = objectMapper.readValue(new File("src/test/resources/TransID.yaml"),typeReference);
        System.out.println(transIdList);
        return transIdList;
    }
}
