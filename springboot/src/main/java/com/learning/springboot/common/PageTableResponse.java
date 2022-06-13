package com.learning.springboot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "列表查询的分页参数",description = "请求参数")
@Data
public class PageTableResponse<T> implements Serializable {

    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "页面",required = true,example ="1")
    private Integer recordsTotal;
    private List<T> data;

}
