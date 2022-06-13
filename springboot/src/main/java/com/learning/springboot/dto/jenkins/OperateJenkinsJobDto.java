package com.learning.springboot.dto.jenkins;

import com.learning.springboot.entity.HogwartsTestUser;
import lombok.Data;

import java.util.Map;

@Data
public class OperateJenkinsJobDto {

    private String token;

    private String jenkinsUrl;

    private String jenkinsUserName;

    private String jenkinsPassWord;

    private HogwartsTestUser hogwartsTestUser;

    private Map<String, String> params;
}
