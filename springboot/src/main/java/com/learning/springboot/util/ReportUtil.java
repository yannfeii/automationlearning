package com.learning.springboot.util;

import com.learning.springboot.dto.jenkins.OperateJenkinsJobDto;
import com.learning.springboot.entity.HogwartsTestJenkins;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class ReportUtil {

    public static void main(String[] args) {

        String buildUrl = "http:///job/hogwarts_test_mini_start_test_1/label=jenkins_slave/2/allure/";

        String allureReportBaseUrl = buildUrl.substring(buildUrl.indexOf("/job"));

        System.out.println("allureReportBaseUrl== "+allureReportBaseUrl);

    }

    /**
     *  获取allure报告地址
     * @param buildUrl 构建地址
     * @param hogwartsTestJenkins Jenkins记录表
     * @param autoLoginJenkinsFlag 是否自动登录Jenkins
     * @return
     */

//    public static String getAllureReportUrl(String buildUrl, OperateJenkinsJobDto operateJenkinsJobDto, boolean autoLoginJenkinsFlag){
//
//        if(StringUtils.isEmpty(buildUrl) || !buildUrl.contains("/job")){
//            return buildUrl;
//        }
//        String allureReportUrl = buildUrl;
//
//        if(autoLoginJenkinsFlag){
//            allureReportUrl = getAllureReportUrlAndLogin(buildUrl, operateJenkinsJobDto);
//        }
//        return allureReportUrl + "allure/";
//    }

    /**
     *  获取可以自动登录Jenkins的allure报告地址
     * @param buildUrl
     * @return
     */
    public static StringBuilder getAllureReportUrlAndLogin(String buildUrl, OperateJenkinsJobDto operateJenkinsJobDto) {

        StringBuilder allureReportUrl = new StringBuilder();
        String jenkinsUrl = operateJenkinsJobDto.getJenkinsUrl();

        buildUrl = buildUrl.replace(jenkinsUrl,"");
        allureReportUrl.append(jenkinsUrl).append("j_acegi_security_check");
        allureReportUrl.append("?j_username=").append(operateJenkinsJobDto.getJenkinsUserName())
                        .append("&j_password=").append(operateJenkinsJobDto.getJenkinsPassWord())
                        .append("&from=").append(buildUrl).append("allure")
                        //.append("&Submit=登录")
        ;

        return allureReportUrl;
    }

}
