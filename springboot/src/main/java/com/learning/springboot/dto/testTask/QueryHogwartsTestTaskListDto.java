package com.learning.springboot.dto.testTask;

import com.learning.springboot.dto.BaseDto;
import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="查询任务信息列表对象")
@Data
public class QueryHogwartsTestTaskListDto extends BaseEntity {

    @ApiModelProperty(value="Jenkins名称")
    private String taskName;

    @ApiModelProperty(value="创建者id(客户端传值无效，以token数据为准)")
    private Integer taskCreateUserId;
}
