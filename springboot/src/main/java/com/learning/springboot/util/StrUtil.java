package com.learning.springboot.util;

import com.learning.springboot.common.Constants;
import org.apache.commons.lang.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StrUtil {

    public static Integer getUserId(HttpServletRequest request){
        String userIdStr =  request.getHeader(Constants.TOKEN);

        //log.info("--------userIdStr-------"+userIdStr);
        Integer userId = null;
        try{
            userId = Integer.parseInt(userIdStr);
        }catch (Exception e){
            throw new RuntimeException("用户ID必须为数字");
        }
        return userId;
    }

    public static String getHostAndPort(String requestUrl) {

        if(StringUtils.isEmpty(requestUrl)){
            return "";
        }

        String http = "";
        String tempUrl = "";
        if(requestUrl.contains("://")){
            http = requestUrl.substring(0,requestUrl.indexOf("://")+3);
            tempUrl = requestUrl.substring(requestUrl.indexOf("://")+3);
        }
        if(tempUrl.contains("/")){
            tempUrl = tempUrl.substring(0,tempUrl.indexOf("/"));
        }
        return http+tempUrl;
    }

    public static String listToIds(List caseIdList){
        return caseIdList.toString().replace("[","").replace("]","");
    }
}
