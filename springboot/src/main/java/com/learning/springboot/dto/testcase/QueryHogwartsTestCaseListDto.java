package com.learning.springboot.dto.testcase;

import com.learning.springboot.dto.BaseDto;
import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="查询测试用例信息列表对象")
@Data
public class QueryHogwartsTestCaseListDto  extends BaseEntity {

    @ApiModelProperty(value="测试用例名称")
    private String caseName;

    @ApiModelProperty(value="创建者id(客户端传值无效，以token数据为准)")
    private Integer createUserId;

}
