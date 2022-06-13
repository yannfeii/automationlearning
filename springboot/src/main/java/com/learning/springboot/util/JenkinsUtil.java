package com.learning.springboot.util;

import com.alibaba.fastjson.JSONObject;
import com.learning.springboot.common.Constants;
import com.learning.springboot.dto.RequestInfoDto;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.jenkins.OperateJenkinsJobDto;
import com.learning.springboot.entity.HogwartsTestTask;
import com.learning.springboot.entity.HogwartsTestUser;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JenkinsUtil {

    public static ResultDto jenkinsBuild(String jobName, String userId, String remark, String command) throws IOException, URISyntaxException {

        String baseUrl =  "http://192.168.19.128:8080/";
        String userName = "admin";
        String passWord = "admin";

//        String jobName = "test_connect_job_2";

        ClassPathResource classPathResource = new ClassPathResource("TestJenkinsConnection.xml");
        InputStream inputStream = classPathResource.getInputStream();

        String jobConfigXml = FileUtil.getText(inputStream);

        JenkinsHttpClient jenkinsHttpClient = new JenkinsHttpClient(new URI(baseUrl),userName,passWord);

        JenkinsServer jenkinsServer = new JenkinsServer(jenkinsHttpClient);

        Map<String, Job> jobMap = jenkinsServer.getJobs();
        log.info("--------jobMap---------"+jobMap.keySet().toString());

        if(jobMap.containsKey(jobName)){
            jenkinsServer.updateJob(jobName,jobConfigXml);
        }else{
            jenkinsServer.createJob(jobName,jobConfigXml,true);
        }

        Job job = jobMap.get(jobName);

        Map<String,String> parameterMap = new HashMap<>();
        parameterMap.put("userId",userId);
        parameterMap.put("remark",remark);
        parameterMap.put("testCommand",command);

        job.build(parameterMap,true);
        log.info(job.getFullName());

        return ResultDto.resultSuccess("success");

    }

    public static StringBuilder updateTaskStatusUrl(RequestInfoDto requestInfoDto, HogwartsTestTask hogwartsTestTask){

        StringBuilder updateTaskStatusUrl = new StringBuilder();
        updateTaskStatusUrl.append("curl -X PUT ");
        updateTaskStatusUrl.append("\""+requestInfoDto.getBaseUrl()+"/task/status\"");
        updateTaskStatusUrl.append("-H 'Content-Type: application/json'");
        updateTaskStatusUrl.append("-H 'token: "+requestInfoDto.getToken()+"'");
        updateTaskStatusUrl.append("-d ");

        JSONObject params = new JSONObject();
        params.put("taskId",hogwartsTestTask.getId());//获取jenkins的全局参数
        params.put("buildUrl","${BUID_URL}");
        params.put("", Constants.STATUS_THREE);

        updateTaskStatusUrl.append(""+params.toJSONString()+"'");

        return updateTaskStatusUrl;
    }

    public static ResultDto<HogwartsTestUser> build2(OperateJenkinsJobDto operateJenkinsJobDto) throws IOException,URISyntaxException{

        String baseUrl = operateJenkinsJobDto.getJenkinsUrl();
        String userName = operateJenkinsJobDto.getJenkinsUserName();
        String passWord = operateJenkinsJobDto.getJenkinsPassWord();

        String jobName = "Start_task_"+operateJenkinsJobDto.getToken();

        ClassPathResource classPathResource = new ClassPathResource("TestJenkinsConnection.xml");
        InputStream inputStream = classPathResource.getInputStream();

        String jobConfigXml = FileUtil.getText(inputStream);

        JenkinsHttpClient jenkinsHttpClient = new JenkinsHttpClient(new URI(baseUrl),userName,passWord);

        JenkinsServer jenkinsServer = new JenkinsServer(jenkinsHttpClient);

        HogwartsTestUser hogwartsTestUser = operateJenkinsJobDto.getHogwartsTestUser();

        String startTestJobName = hogwartsTestUser.getStartTestJobName();

        //保证job的配置文件实时更新
        if(StringUtils.isEmpty(startTestJobName)){
            jenkinsServer.createJob(jobName,jobConfigXml,true);//true登录安全设置
        }else {
            jenkinsServer.updateJob(jobName,jobConfigXml,true);
        }

        Map<String, Job> jobMap = jenkinsServer.getJobs();

        if(jobMap.containsKey(jobName)){
            jenkinsServer.updateJob(jobName,jobConfigXml);
            hogwartsTestUser.setStartTestJobName(jobName);

        }else{
            jenkinsServer.createJob(jobName,jobConfigXml,true);
        }

        Job job = jobMap.get(jobName);

        Map<String,String> parameterMap = operateJenkinsJobDto.getParams();

        job.build(parameterMap,true);

        return ResultDto.resultSuccess("success",hogwartsTestUser);
    }
}
