package com.learning.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.learning.springboot.common.Constants;
import com.learning.springboot.common.PageTableRequest;
import com.learning.springboot.common.PageTableResponse;
import com.learning.springboot.common.TokenDb;
import com.learning.springboot.dto.RequestInfoDto;
import com.learning.springboot.dto.TokenDto;
import com.learning.springboot.dto.testTask.*;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.entity.HogwartsTestTask;
import com.learning.springboot.service.HogwartsTestTaskService;
import com.learning.springboot.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@ApiModel(value = "测试用例管理")
@RequestMapping("testTask")
@RestController
@Slf4j
public class HogwartsTestTaskController {

    @Autowired
    private HogwartsTestTaskService hogwartsTestTaskService;

    @Autowired
    private TokenDb tokenDb;

    @ApiOperation("列表查询")
    @GetMapping("list")
    //http://localhost:8080/testCase/list?pageNum=1&pageSize=10
    public ResultDto<PageTableResponse<HogwartsTestTask>> list(HttpServletRequest request, PageTableRequest<QueryHogwartsTestTaskListDto> pageTableRequest){

        log.info("任务列表查询-入参= "+ JSONObject.toJSONString(pageTableRequest));

        if(Objects.isNull(pageTableRequest)){
            return ResultDto.resultFail("列表查询参数不能为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));
        QueryHogwartsTestTaskListDto params = pageTableRequest.getParams();

        if(Objects.isNull(params)){
            params = new QueryHogwartsTestTaskListDto();
        }

        params.setTaskCreateUserId(tokenDto.getUserId());
        pageTableRequest.setParams(params);

//        Integer userId = StrUtil.getUserId(request);
//        Map<String, Object> params = pageTableRequest.getParams();
//
//        if(Objects.isNull(params)){
//            params = new HashMap();
//        }
//
//        params.put("createUserId",userId);
//        pageTableRequest.setParams(params);

        ResultDto<PageTableResponse<HogwartsTestTask>> responseResultDto = hogwartsTestTaskService.taskList(pageTableRequest);
        return responseResultDto;

    }
    @ApiOperation("测试任务新增")
    @PostMapping
    //http://localhost:8080/testCase/
    public ResultDto<HogwartsTestTask> saveTestTask(HttpServletRequest request,
                                                      @RequestBody TestTaskDto testTaskDto){

        log.info("测试任务新增-入参= "+ JSONObject.toJSONString(testTaskDto));

        if(Objects.isNull(testTaskDto)){
            return ResultDto.resultFail("测试任务入参为空");
        }
        AddHogwartsTestTaskDto addHogwartsTestTaskDto = testTaskDto.getTestTask();

        if(Objects.isNull(addHogwartsTestTaskDto)){
            return ResultDto.resultFail("测试任务为空");
        }
        if(StringUtils.isEmpty(addHogwartsTestTaskDto.getTaskName())){
            return ResultDto.resultFail("测试任务名称为空");
        }

        List<Integer> caseIdList = testTaskDto.getCaseIdList();

        if(Objects.isNull(caseIdList) || caseIdList.size()==0){
            return ResultDto.resultFail("测试用例为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        addHogwartsTestTaskDto.setCreateUserId(tokenDto.getUserId());
        addHogwartsTestTaskDto.setTaskJenkinsId(tokenDto.getDefaultJenkinsId());

        return hogwartsTestTaskService.saveTask(testTaskDto,Constants.TASK_TYPE_ONE);

    }

    @ApiOperation("测试任务修改")
    @PutMapping()
    //http://localhost:8080/testCase/
    public ResultDto<HogwartsTestTask> updateTestTask(HttpServletRequest request,
                                                      @RequestBody UpdateHogwartsTestTaskDto updateHogwartsTestTaskDto){

        log.info("测试任务修改-入参= "+ JSONObject.toJSONString(updateHogwartsTestTaskDto));

        if(Objects.isNull(updateHogwartsTestTaskDto)){
            return ResultDto.resultFail("任务参数为空");
        }
        if(Objects.isNull(updateHogwartsTestTaskDto.getId())){
            return ResultDto.resultFail("任务ID为空");
        }
        if(StringUtils.isEmpty(updateHogwartsTestTaskDto.getTaskName())){
            return ResultDto.resultFail("任务名称为空");
        }
        if(StringUtils.isEmpty(updateHogwartsTestTaskDto.getTaskData())){
            return ResultDto.resultFail("任务数据为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        HogwartsTestTask hogwartsTestTask = new HogwartsTestTask();

        BeanUtils.copyProperties(updateHogwartsTestTaskDto,hogwartsTestTask);
        hogwartsTestTask.setCreateUserId(tokenDto.getUserId());

        return hogwartsTestTaskService.updateTask(hogwartsTestTask);

    }

    @ApiOperation("根据ID查询测试任务")
    @GetMapping("/{taskId}")
    //http://localhost:8080/taskId/13
    public ResultDto<HogwartsTestTask> getTaskById(HttpServletRequest request,
                                                   @PathVariable("taskId") Integer taskId){

        log.info("根据ID查询测试任务-入参= "+ taskId);

        if(Objects.isNull(taskId)){
            return ResultDto.resultFail("任务ID为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        return hogwartsTestTaskService.getTaskById(tokenDto.getUserId(), taskId);
    }

    @ApiOperation("根据ID删除测试任务")
    @DeleteMapping("{taskId}")
    public ResultDto deleteTask(@PathVariable("taskId") Integer taskId, Integer createTaskId){

        log.info("根据ID删除测试任务： "+taskId);
        return hogwartsTestTaskService.deleteTask(taskId, createTaskId);
    }

    @ApiOperation("修改测试任务状态")
    @PutMapping("status")
    public ResultDto<HogwartsTestTask> updateTaskStatus(HttpServletRequest request,
                  @RequestBody UpdateHogwartsTestTaskDto updateHogwartsTestTaskDto){

        if(Objects.isNull(updateHogwartsTestTaskDto)){
            return ResultDto.resultFail("测试任务状态信息为空");
        }
        Integer taskId = updateHogwartsTestTaskDto.getTaskId();
        String  buildUrl = updateHogwartsTestTaskDto.getBuildUrl();
        Integer taskStatus = updateHogwartsTestTaskDto.getStatus();

        if(Objects.isNull(taskId)){
            return ResultDto.resultFail("测试任务ID为空");
        }

        if(Objects.isNull(buildUrl)){
            return ResultDto.resultFail("测试任务build URL为空");
        }

        if(Objects.isNull(taskStatus)){
            return ResultDto.resultFail("测试任务状态ID为空");
        }
        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        HogwartsTestTask hogwartsTestTask = new HogwartsTestTask();

        hogwartsTestTask.setId(taskId);
        hogwartsTestTask.setBuildUrl(buildUrl);
        hogwartsTestTask.setStatus(taskStatus);
        hogwartsTestTask.setCreateUserId(tokenDto.getUserId());

        return hogwartsTestTaskService.updateTaskStatus(hogwartsTestTask);
    }

    @ApiOperation("获取allure报告")
    @GetMapping("/allureReport/{taskId}")
    public ResultDto getAllureReport(HttpServletRequest request,@PathVariable Integer taskId){

        log.info("获取allure报告 入参： "+taskId);
        if(Objects.isNull(taskId)){
            return ResultDto.resultFail("测试任务ID为空");
        }

        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));

        HogwartsTestTask hogwartsTestTask = new HogwartsTestTask();
        hogwartsTestTask.setId(taskId);
        hogwartsTestTask.setCreateUserId(tokenDto.getUserId());

        ResultDto<String> resultDto = hogwartsTestTaskService.getAllureReport(hogwartsTestTask);

        //String allureReportUrl = "http://";
        return resultDto;
    }

    @ApiOperation("开始测试")
    @PostMapping("start")
    public ResultDto taskStart(HttpServletRequest request,@RequestBody StartTestDto startTestDto) throws IOException {

        log.info("开始测试 入参： "+JSONObject.toJSONString(startTestDto));

        if(Objects.isNull(startTestDto)){
            ResultDto.resultFail("参数为空");
        }

        Integer taskId = startTestDto.getTestId();

        if(Objects.isNull(taskId)){
            ResultDto.resultFail("taskId为空");
        }

        String token = request.getHeader(Constants.LOGIN_TOKEN);
        log.info("token== "+token);

        HogwartsTestTask hogwartsTestTask = new HogwartsTestTask();
        hogwartsTestTask.setId(startTestDto.getTestId());
        hogwartsTestTask.setTestCommand(startTestDto.getTestCommand());
        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(Constants.LOGIN_TOKEN));
        hogwartsTestTask.setCreateUserId(tokenDto.getUserId());
        hogwartsTestTask.setTestJenkinsId(tokenDto.getDefaultJenkinsId());


        String url = request.getRequestURL().toString();
        log.info("请求地址== "+url);
        url = StrUtil.getHostAndPort(request.getRequestURL().toString());

        RequestInfoDto requestInfoDto = new RequestInfoDto();
        requestInfoDto.setBaseUrl(url);
        requestInfoDto.setRequestUrl(url);
        requestInfoDto.setToken(token);
        log.info("requestInfoDto== "+ JSONObject.toJSONString(requestInfoDto));
        return hogwartsTestTaskService.startTask(tokenDto, requestInfoDto, hogwartsTestTask);
    }
}
