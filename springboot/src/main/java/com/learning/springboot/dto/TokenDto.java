package com.learning.springboot.dto;

import lombok.Data;

@Data
public class TokenDto {

    private Integer userId;

    private String userName;

    private Integer defaultJenkinsId;

    private String token;
}
