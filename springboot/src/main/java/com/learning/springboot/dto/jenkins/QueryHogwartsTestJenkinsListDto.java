package com.learning.springboot.dto.jenkins;

import com.learning.springboot.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="查询Jenkins信息列表对象")
@Data
public class QueryHogwartsTestJenkinsListDto extends BaseDto {

    @ApiModelProperty(value="Jenkins名称")
    private String name;

    @ApiModelProperty(value="创建者id(客户端传值无效，以token数据为准)")
    private Integer createUserId;
}
