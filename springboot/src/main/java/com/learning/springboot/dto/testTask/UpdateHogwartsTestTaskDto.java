package com.learning.springboot.dto.testTask;

import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "测试任务修改类")
@Data
public class UpdateHogwartsTestTaskDto extends BaseEntity {

    @ApiModelProperty(value = "测试任务ID", required = true)
    private Integer id;

    @ApiModelProperty(value = "测试任务数据", required = true)
    private String taskData;

    @ApiModelProperty(value = "测试任务名称", required = true)
    private String taskName;

    @ApiModelProperty(value = "测试任务备注")
    private String remark;

    @ApiModelProperty(value = "测试任务构建ID", required = true)
    private Integer taskId;

    @ApiModelProperty(value = "测试任务构建地址", required = true)
    private String buildUrl;

    @ApiModelProperty(value = "测试任务状态", required = true)
    private Integer status;


}
