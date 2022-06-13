package com.learning.springboot.dto.jenkins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户登录类", description = "请求类")
@Data
public class JenkinsDto {

    @ApiModelProperty(value = "Job名称",example = "Job_1",required = true)
    private String jobName;
    @ApiModelProperty(value = "用户ID",example = "admin",required = true)
    private String userID;
    @ApiModelProperty(value = "备注",example = "admin",required = true)
    private String remark;
    @ApiModelProperty(value = "测试命令",example = "pwd",required = true)
    private String command;
}
