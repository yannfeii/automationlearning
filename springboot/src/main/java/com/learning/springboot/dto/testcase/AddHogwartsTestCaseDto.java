package com.learning.springboot.dto.testcase;

import com.learning.springboot.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "添加测试用例对象")
@Data
public class AddHogwartsTestCaseDto extends BaseEntity {

    @ApiModelProperty(value = "测试用例数据", notes = "文件类型case时不传值", required = true)
    private String caseData;

    @ApiModelProperty(value = "测试用例名称", required = true)
    private String caseName;

    @ApiModelProperty(value = "测试用例备注")
    private String remark;
}
