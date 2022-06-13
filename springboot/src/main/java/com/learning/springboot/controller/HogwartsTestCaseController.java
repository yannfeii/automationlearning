package com.learning.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.learning.springboot.common.Constants;
import com.learning.springboot.common.PageTableRequest;
import com.learning.springboot.common.PageTableResponse;
import com.learning.springboot.common.TokenDb;
import com.learning.springboot.dto.*;
import com.learning.springboot.dto.testcase.AddHogwartsTestCaseDto;
import com.learning.springboot.dto.testcase.QueryHogwartsTestCaseListDto;
import com.learning.springboot.dto.testcase.UpdateHogwartsTestCaseDto;
import com.learning.springboot.entity.HogwartsTestCase;
import com.learning.springboot.service.HogwartsTestCaseService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@ApiModel(value = "测试用例管理")
@RequestMapping("testCase")
@RestController
@Slf4j
public class HogwartsTestCaseController {

    @Autowired
    private HogwartsTestCaseService hogwartsTestCaseService;

    @Autowired
    private TokenDb tokenDb;

    @ApiOperation("列表查询")
    @GetMapping("list")
    public ResultDto<PageTableResponse<HogwartsTestCase>> list(HttpServletRequest request,PageTableRequest<QueryHogwartsTestCaseListDto> pageTabReq){
        //http://localhost:8080/testCase/list?pageNum=1&pageSize=10

        if(Objects.isNull(pageTabReq)){
            return ResultDto.resultSuccess("列表查询参数不能为空");
        }
        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));
        QueryHogwartsTestCaseListDto params = pageTabReq.getParams();
        log.info("------testCase list params--------"+params);

        if(Objects.isNull(params)){
            params = new QueryHogwartsTestCaseListDto();
        }
        params.setCreateUserId(tokenDto.getUserId());
        pageTabReq.setParams(params);

//        Integer userId = StrUtil.getUserId(request);
//
//        Map params = pageTabReq.getParams();
//        if(Objects.isNull(params)){
//            params = new HashMap();
//        }
//
//        params.put("createUserId",userId);
//        pageTabReq.setParams(params);

       ResultDto<PageTableResponse<HogwartsTestCase>> responseResultDto = hogwartsTestCaseService.caselist(pageTabReq);
        return responseResultDto;

    }

    @ApiOperation("文件类型用例上传")
    @PostMapping("file")
    //http://localhost:8080/testCase/file 需要放token,选文件
    public ResultDto<HogwartsTestCase> uploadFile(HttpServletRequest request, @RequestParam("caseFile") MultipartFile caseFile,
                                         AddHogwartsTestCaseDto addHogwartsTestCaseDto) throws IOException {

        log.info("=====新增文件测试用例·请求入参"+ JSONObject.toJSONString(addHogwartsTestCaseDto));

        if(Objects.isNull(caseFile)){
            return ResultDto.resultFail("文件为空");
        }
        if(Objects.isNull(addHogwartsTestCaseDto)){
            return ResultDto.resultFail("参数为空");
        }
        if(StringUtils.isEmpty(addHogwartsTestCaseDto.getCaseName())){
            return ResultDto.resultFail("用例名称为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        InputStream inputStream = caseFile.getInputStream();
        String caseData = IOUtils.toString(inputStream,"UTF-8");
        inputStream.close();

        HogwartsTestCase hogwartsTestCase = new HogwartsTestCase();
        hogwartsTestCase.setCaseData(caseData);
        hogwartsTestCase.setCaseName(addHogwartsTestCaseDto.getCaseName());
        hogwartsTestCase.setRemark(addHogwartsTestCaseDto.getRemark());
        hogwartsTestCase.setCreateUserId(tokenDto.getUserId());
        return hogwartsTestCaseService.saveDB(hogwartsTestCase);

    }

    @ApiOperation("文本类型用例添加")
    @PostMapping("text")
    //http://localhost:8080/testCase/text
    public ResultDto<HogwartsTestCase> uploadText(HttpServletRequest request,
                                                  @RequestBody AddHogwartsTestCaseDto addHogwartsTestCaseDto){

        if(Objects.isNull(addHogwartsTestCaseDto)){
            return ResultDto.resultFail("参数为空");
        }
        if(StringUtils.isEmpty(addHogwartsTestCaseDto.getCaseName())){
            return ResultDto.resultFail("用例名称为空");
        }
        if(StringUtils.isEmpty(addHogwartsTestCaseDto.getCaseData())){
            return ResultDto.resultFail("用例数据为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        HogwartsTestCase hogwartsTestCase = new HogwartsTestCase();

        BeanUtils.copyProperties(addHogwartsTestCaseDto,hogwartsTestCase);
        hogwartsTestCase.setCreateUserId(tokenDto.getUserId());

        return hogwartsTestCaseService.saveDB(hogwartsTestCase);

    }

    @ApiOperation("文本类型用例修改")
    @PutMapping("text")
    //http://localhost:8080/testCase/
    public ResultDto<HogwartsTestCase> updateTestCase(HttpServletRequest request,
                                                  @RequestBody UpdateHogwartsTestCaseDto updateHogwartsTestCaseDto){

        if(Objects.isNull(updateHogwartsTestCaseDto)){
            return ResultDto.resultFail("参数为空");
        }
        if(Objects.isNull(updateHogwartsTestCaseDto.getId())){
            return ResultDto.resultFail("用例ID为空");
        }
        if(StringUtils.isEmpty(updateHogwartsTestCaseDto.getCaseName())){
            return ResultDto.resultFail("用例名称为空");
        }
        if(StringUtils.isEmpty(updateHogwartsTestCaseDto.getCaseData())){
            return ResultDto.resultFail("用例数据为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        HogwartsTestCase hogwartsTestCase = new HogwartsTestCase();

        BeanUtils.copyProperties(updateHogwartsTestCaseDto,hogwartsTestCase);
        hogwartsTestCase.setCreateUserId(tokenDto.getUserId());

        return hogwartsTestCaseService.updateCase(hogwartsTestCase);

    }

    @ApiOperation("根据ID查询测试用例")
    @GetMapping("/{caseId}")
    public ResultDto getCaseById(@PathVariable("caseId") Integer caseId){
        //http://localhost:8080/testCase/13
        log.info("根据ID查询测试用例： "+caseId);
        return hogwartsTestCaseService.getCaseById(caseId);
    }

}
