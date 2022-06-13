package com.learning.springboot.dto.testTask;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="执行测试任务类")
@Data
public class StartTestDto {

    @ApiModelProperty(value="测试任务ID",required=true)
    private Integer testId;

    @ApiModelProperty(value="测试用例执行命令", example="mvn test")
    private String testCommand;
}
