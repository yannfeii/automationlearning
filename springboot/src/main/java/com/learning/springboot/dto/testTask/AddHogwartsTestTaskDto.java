package com.learning.springboot.dto.testTask;

import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="添加任务对象")
@Data
public class AddHogwartsTestTaskDto extends BaseEntity {

    /**
     * 名称
     */
    @ApiModelProperty(value="任务名称",required=true)
    private String taskName;

    /**
     * 运行测试的Jenkins服务器id
     */
    @ApiModelProperty(value="运行测试的Jenkins服务器id",required=true)
    private Integer taskJenkinsId;

    /**
     * 备注
     */
    @ApiModelProperty(value="任务备注")
    private String remark;

    @ApiModelProperty(value="创建者id(客户端传值无效，以token数据为准)")
    private Integer createUserId;
}
