package com.learning.springboot.dto.testUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户登录类", description = "请求类")
@Data
public class TestUserDto {

    @ApiModelProperty(value = "user name",example = "Hogwarts",required = true)
    private String userName;
    @ApiModelProperty(value = "user passwrod",example = "Hogwarts123",required = true)
    private String password;
    @ApiModelProperty(hidden = true)
    private String token;


//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }



}
