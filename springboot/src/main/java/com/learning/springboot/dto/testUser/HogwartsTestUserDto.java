package com.learning.springboot.dto.testUser;

import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户注册类",description = "请求类")
@Data
public class HogwartsTestUserDto extends BaseEntity {

    @ApiModelProperty( value = "用户名", example = "tester", required = true)
    private String userName;

    @ApiModelProperty( value = "密码", example = "password", required = true)
    private String password;

    @ApiModelProperty( value = "邮箱", example = "tester@xxx.com")
    private String email;

    private String autoCreateCaseJobName;

    private String startTestJobName;

    private Integer defaultJenkinsId;

}
