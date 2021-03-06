package com.learning.springboot.service.impl;

import com.learning.springboot.common.Constants;
import com.learning.springboot.common.PageTableRequest;
import com.learning.springboot.common.PageTableResponse;
import com.learning.springboot.dao.HogwartsTestCaseMapper;
import com.learning.springboot.dao.HogwartsTestTaskCaseRelMapper;
import com.learning.springboot.dao.HogwartsTestTaskMapper;
import com.learning.springboot.dao.HogwartsTestUserMapper;
import com.learning.springboot.dto.RequestInfoDto;
import com.learning.springboot.dto.TokenDto;
import com.learning.springboot.dto.jenkins.OperateJenkinsJobDto;
import com.learning.springboot.dto.testTask.AddHogwartsTestTaskDto;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.testTask.QueryHogwartsTestTaskListDto;
import com.learning.springboot.dto.testTask.TestTaskDto;
import com.learning.springboot.entity.HogwartsTestCase;
import com.learning.springboot.entity.HogwartsTestTask;
import com.learning.springboot.entity.HogwartsTestTaskCaseRel;
import com.learning.springboot.entity.HogwartsTestUser;
import com.learning.springboot.service.HogwartsTestTaskService;
import com.learning.springboot.util.JenkinsUtil;
import com.learning.springboot.util.ReportUtil;
import com.learning.springboot.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.ServerException;
import java.util.*;

@Service
@Slf4j
public class HogwartsTestTaskServiceImpl implements HogwartsTestTaskService {

    @Autowired
    private HogwartsTestTaskMapper hogwartsTestTaskMapper;

    @Autowired
    private HogwartsTestCaseMapper hogwartsTestCaseMapper;

    @Autowired
    private HogwartsTestTaskCaseRelMapper hogwartsTestTaskCaseRelMapper;

    @Autowired
    private HogwartsTestUserMapper hogwartsTestUserMapper;

    @Value("${jenkins.url}")
    private String jenkinsUrl;
    @Value("${jenkins.username}")
    private String jenkinsUserName;
    @Value("${jenkins.password}")
    private String jenkinsPassWord;
    @Value("${jenkins.casetype}")
    private Integer jenkinsCaseType;
    @Value("${jenkins.casesuffix}")
    private String jenkinsCaseSuffix;
    @Value("${jenkins.testcommand}")
    private String jenkinsTestCommand;


    @Override
    public ResultDto<PageTableResponse<HogwartsTestTask>> taskList(PageTableRequest<QueryHogwartsTestTaskListDto> pageTableRequest) {

        QueryHogwartsTestTaskListDto params = pageTableRequest.getParams();
        Integer pageNum = pageTableRequest.getPageNum();
        Integer pageSize = pageTableRequest.getPageSize();
        //?????????
        Integer taskCount = hogwartsTestTaskMapper.taskCount(params);
        //????????????
        List<HogwartsTestTask> hogwartsTestTask =  hogwartsTestTaskMapper.taskList(params,(pageNum-1)*pageSize,pageSize);

        PageTableResponse<HogwartsTestTask> pageTabRes = new PageTableResponse<>();
        pageTabRes.setRecordsTotal(taskCount);
        pageTabRes.setData(hogwartsTestTask);

        return ResultDto.resultSuccess("??????",pageTabRes);
    }

    @Override
    public ResultDto startTask(TokenDto tokenDto, RequestInfoDto requestInfoDto, HogwartsTestTask hogwartsTestTask) throws IOException {

        //1.??????????????????
        if(Objects.isNull(hogwartsTestTask)){
            return ResultDto.resultFail("??????????????????");
        }

        Integer taskId = hogwartsTestTask.getId();

        if(Objects.isNull(taskId)){
            return ResultDto.resultFail("taskId??????");
        }

        HogwartsTestTask queryHogwarTestTask = new HogwartsTestTask();
        queryHogwarTestTask.setId(taskId);
        queryHogwarTestTask.setCreateUserId(hogwartsTestTask.getCreateUserId());

        HogwartsTestTask result = hogwartsTestTaskMapper.selectOne(queryHogwarTestTask);

        if(Objects.isNull(result)){
           return ResultDto.resultFail("?????????????????????");
        }

        HogwartsTestUser hogwartsTestUser = new HogwartsTestUser();
        hogwartsTestUser.setId(tokenDto.getUserId());

        HogwartsTestUser queryHogwartsTestUser = hogwartsTestUserMapper.selectOne(hogwartsTestUser);

        if(Objects.isNull(queryHogwartsTestUser)){
            return ResultDto.resultFail("??????user?????????");
        }

        String startTestJobName = hogwartsTestUser.getStartTestJobName();

        result.setStatus(Constants.STATUS_TWO);
        result.setUpdateTime(new Date());

        hogwartsTestTaskMapper.updateByPrimaryKeySelective(result);

        //??????Jenkins??????????????????????????? ???????????????????????????????????? ??????????????????+????????????
        StringBuilder updateStatusUrl = JenkinsUtil.updateTaskStatusUrl(requestInfoDto,hogwartsTestTask);
        updateStatusUrl.append("\n");
        //??????Jenkins????????????
        Map<String, String> params =  new HashMap();

        params.put("aitesBaseUrl",requestInfoDto.getBaseUrl());
        params.put("token",requestInfoDto.getToken());
        params.put("testCommand",result.getTestCommand());
        params.put("updateStatusData",updateStatusUrl.toString());

        //??????Jenkins
        OperateJenkinsJobDto operateJenkinsJobDto = new OperateJenkinsJobDto();

        operateJenkinsJobDto.setToken(requestInfoDto.getToken());
        operateJenkinsJobDto.setJenkinsUrl(jenkinsUrl);
        operateJenkinsJobDto.setJenkinsUserName(jenkinsUserName);
        operateJenkinsJobDto.setJenkinsPassWord(jenkinsPassWord);
        operateJenkinsJobDto.setHogwartsTestUser(hogwartsTestUser);
        operateJenkinsJobDto.setParams(params);

        ResultDto<HogwartsTestUser> resultDto = new ResultDto<>();
        resultDto.setAsSuccess();

        try {
            resultDto = JenkinsUtil.build2(operateJenkinsJobDto);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if(resultDto.getResultCode() == 1){
            throw new ServerException("??????????????????"+resultDto.getMessage());
        }
        //??????????????????
        if(StringUtils.isEmpty(startTestJobName)){
            HogwartsTestUser buildResultUser = resultDto.getData();
            hogwartsTestUserMapper.updateByPrimaryKeySelective(buildResultUser);
        }

        return ResultDto.resultSuccess("startTask success");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto<HogwartsTestTask> saveTask(TestTaskDto testTaskDto, Integer taskType) {

        List<Integer> caseIdList = testTaskDto.getCaseIdList();
        AddHogwartsTestTaskDto testTask = testTaskDto.getTestTask();

        String ids = StrUtil.listToIds(caseIdList);

        StringBuilder testCommand = new StringBuilder();

        List<HogwartsTestCase> hogwartsTestCaseList = hogwartsTestCaseMapper.selectByIds(ids);

        geneTestCommand(testCommand,hogwartsTestCaseList);


        HogwartsTestTask hogwartsTestTask = new HogwartsTestTask();

        hogwartsTestTask.setStatus(Constants.STATUS_ONE);
        hogwartsTestTask.setCreateUserId(testTask.getCreateUserId());
        hogwartsTestTask.setTestCommand(testCommand.toString());
        hogwartsTestTask.setCaseCount(caseIdList.size());
        hogwartsTestTask.setName(testTask.getTaskName());
        hogwartsTestTask.setTaskType(1);
        hogwartsTestTask.setRemark(testTask.getRemark());
        hogwartsTestTask.setCreateTime(new Date());
        hogwartsTestTask.setUpdateTime(new Date());

        hogwartsTestTaskMapper.insert(hogwartsTestTask);

        List<HogwartsTestTaskCaseRel> hogwartsTestTaskCaseRelList = new ArrayList<>();

        for(Integer caseId:caseIdList){

            HogwartsTestTaskCaseRel hogwartsTestTaskCaseRel = new HogwartsTestTaskCaseRel();

            hogwartsTestTaskCaseRel.setTaskId(hogwartsTestTask.getId());
            hogwartsTestTaskCaseRel.setCaseId(caseId);
            hogwartsTestTaskCaseRel.setCreateTime(new Date());
            hogwartsTestTaskCaseRel.setUpdateTime(new Date());
            hogwartsTestTaskCaseRel.setCreateUserId(testTask.getCreateUserId());

            hogwartsTestTaskCaseRelList.add(hogwartsTestTaskCaseRel);

        }
        hogwartsTestTaskCaseRelMapper.insertList(hogwartsTestTaskCaseRelList);

        return ResultDto.resultSuccess("????????????????????????",hogwartsTestTask);
    }

    private void geneTestCommand(StringBuilder testCommand, List<HogwartsTestCase> hogwartsTestCaseList){

        testCommand.append("pwd");
        testCommand.append("\n");

        String sysCommand = jenkinsTestCommand + " --alluredir=${WORKSPACE}/target/allure-results";

        if(jenkinsCaseType == 1){

            for(HogwartsTestCase hogwartsTestCase:hogwartsTestCaseList){

                testCommand.append(sysCommand).append(" ");
                testCommand.append(hogwartsTestCase.getCaseData()).append("\n");
            }

        }

        if(jenkinsCaseType == 2){

            for(HogwartsTestCase hogwartsTestCase:hogwartsTestCaseList){

                geneCurlCommand(testCommand,hogwartsTestCase,jenkinsCaseSuffix);
                testCommand.append("/n");

                testCommand.append(sysCommand).append(" ");

                testCommand.append(hogwartsTestCase.getCaseName())
                        .append(".")
                        .append(jenkinsCaseSuffix)
                        .append(" || true")
                        .append("\n");
                testCommand.append(hogwartsTestCase.getCaseData()).append("\n");
            }

            testCommand.append("\n");

        }
    }

    @Override
    public ResultDto<HogwartsTestTask> deleteTask(Integer taskId, Integer createTaskId) {

        HogwartsTestTask query = new HogwartsTestTask();
        query.setId(taskId);
        query.setCreateUserId(createTaskId);

        HogwartsTestTask taskResult = hogwartsTestTaskMapper.selectOne(query);

        if(Objects.isNull(taskResult)){
            return ResultDto.resultFail("???????????????????????????");
        }

        hogwartsTestTaskMapper.deleteByPrimaryKey(taskId);
        return ResultDto.resultSuccess("????????????????????????");
    }

    @Override
    public ResultDto<HogwartsTestTask> updateTask(HogwartsTestTask testTask) {

        HogwartsTestTask query =  new HogwartsTestTask();
        query.setCreateUserId(testTask.getCreateUserId());
        query.setId(testTask.getId());

        HogwartsTestTask taskResult = hogwartsTestTaskMapper.selectOne(query);

        if(Objects.isNull(taskResult))
            return ResultDto.resultFail("???????????????????????????");

        testTask.setUpdateTime(new Date());
        testTask.setName(testTask.getName());
        testTask.setRemark(taskResult.getRemark());

        //????????????????????????????????????????????????????????????
        hogwartsTestTaskMapper.updateByPrimaryKeySelective(testTask);

        return ResultDto.resultSuccess("??????????????????",testTask);
    }

    @Override
    public ResultDto<HogwartsTestTask> getTaskById(Integer taskId, Integer createTaskId) {

        HogwartsTestTask query = new HogwartsTestTask();
        query.setId(taskId);

        HogwartsTestTask testTaskResult = hogwartsTestTaskMapper.selectOne(query);

        if(Objects.isNull(testTaskResult)){
            return ResultDto.resultFail("???????????????????????????");
        }

        return ResultDto.resultSuccess("????????????????????????",testTaskResult);
    }

    @Override
    public ResultDto<HogwartsTestTask> updateTaskStatus(HogwartsTestTask hogwartsTestTask) {

        HogwartsTestTask queryHogwartsTestTask = new HogwartsTestTask();
        queryHogwartsTestTask.setId(hogwartsTestTask.getId());
        queryHogwartsTestTask.setCreateUserId(hogwartsTestTask.getCreateUserId());

        HogwartsTestTask testTaskResult = hogwartsTestTaskMapper.selectOne(queryHogwartsTestTask);

        if(Objects.isNull(testTaskResult)){
            return ResultDto.resultFail("???????????????????????????");
        }
        //?????????
        if(Constants.STATUS_THREE.equals(testTaskResult.getStatus())){
            return ResultDto.resultFail("?????????????????????");
        }
        testTaskResult.setUpdateTime(new Date());

        if(Constants.STATUS_THREE.equals(hogwartsTestTask.getStatus())){
            testTaskResult.setBuildUrl(hogwartsTestTask.getBuildUrl());
            testTaskResult.setStatus(Constants.STATUS_THREE);
            hogwartsTestTaskMapper.updateByPrimaryKey(testTaskResult);
        }
        return ResultDto.resultSuccess("??????????????????????????????",testTaskResult);
    }

    @Override
    public ResultDto getAllureReport(HogwartsTestTask hogwartsTestTask) {

        if(Objects.isNull(hogwartsTestTask)){
            return ResultDto.resultFail("????????????????????????");
        }
        Integer taskId = hogwartsTestTask.getId();

        if(Objects.isNull(taskId)){
            return ResultDto.resultFail("??????taskId??????");
        }

        HogwartsTestTask queryHogwartsTestTask = new HogwartsTestTask();

        queryHogwartsTestTask.setId(hogwartsTestTask.getId());
        queryHogwartsTestTask.setCreateUserId(hogwartsTestTask.getCreateUserId());

        HogwartsTestTask testTaskResult = hogwartsTestTaskMapper.selectOne(queryHogwartsTestTask);

        if(Objects.isNull(testTaskResult)){
            return ResultDto.resultFail("???????????????????????????");
        }

        String buildUrl = testTaskResult.getBuildUrl();

        if(Objects.isNull(buildUrl)){
            return ResultDto.resultFail("?????????????????????buildUrl??????");
        }

        OperateJenkinsJobDto operateJenkinsJobDto = new OperateJenkinsJobDto();

        operateJenkinsJobDto.setJenkinsUrl(jenkinsUrl);
        operateJenkinsJobDto.setJenkinsUserName(jenkinsUserName);
        operateJenkinsJobDto.setJenkinsPassWord(jenkinsPassWord);

        StringBuilder allureReportUrl = ReportUtil.getAllureReportUrlAndLogin(buildUrl,operateJenkinsJobDto);

        return ResultDto.resultSuccess("??????", allureReportUrl.toString());
    }

    /**
     *  ?????????????????????curl??????
     * @param testCommand
     * @param hogwartsTestCase
     * @param commandRunCaseSuffix
     */
    private void geneCurlCommand(StringBuilder testCommand, HogwartsTestCase hogwartsTestCase, String commandRunCaseSuffix) {

        //??????curl??????????????????????????????????????????
        testCommand.append("curl ")
                .append("-o ");

        String caseName = hogwartsTestCase.getCaseName();

        if(StringUtils.isEmpty(caseName)){
            caseName = "???????????????????????????";
        }

        testCommand.append(caseName)
                .append(".")
                .append(commandRunCaseSuffix)
                .append(" ${aitestBaseUrl}testCase/data/")
                .append(hogwartsTestCase.getId())
                .append(" -H \"token: ${token}\" ");

        //?????????????????????????????????????????????????????????
        testCommand.append(" || true");

        testCommand.append("\n");
    }
}
