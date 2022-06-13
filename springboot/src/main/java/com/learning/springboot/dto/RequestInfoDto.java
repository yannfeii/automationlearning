package com.learning.springboot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="Jenkins回调")
@Data
public class RequestInfoDto {

    @ApiModelProperty(value = "请求的接口地址，用于拼装命令",hidden = true)
    private String requestUrl;
    @ApiModelProperty(value = "请求的服务器地址，用于拼装命令",hidden = true)
    private String baseUrl;
    @ApiModelProperty(value = "文件服务器地址，用于拼装命令",hidden = true)
    private String filerServer;
    @ApiModelProperty(value = "token信息，用于拼装命令",hidden = true)
    private String token;
    @ApiModelProperty(value = "操作类型",example = "2")
    private Integer operType;
}
