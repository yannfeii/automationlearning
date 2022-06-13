package com.learning.springboot.dto.testcase;

import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "测试用例修改类")
@Data
public class UpdateHogwartsTestCaseDto extends BaseEntity {

    @ApiModelProperty(value = "测试用例ID", required = true)
    private Integer id;

    @ApiModelProperty(value = "测试用例数据", notes = "文件类型case时不传值", required = true)
    private String caseData;

    @ApiModelProperty(value = "测试用例名称", required = true)
    private String caseName;

    @ApiModelProperty(value = "测试用例备注")
    private String remark;
}
