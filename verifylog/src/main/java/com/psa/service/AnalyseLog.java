package com.psa.service;

import com.mysql.jdbc.StringUtils;
import com.psa.entity.AleLog;
import com.psa.util.CsvUtil;
import com.psa.util.YamlUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AnalyseLog {

    public void getPayload(AleLog aleLog, Map<String, List<String>> transIdList){

        if(StringUtils.isNullOrEmpty(aleLog.getSourceSystemM())){
            if((transIdList.get("transID").contains(aleLog.getSourceSystemM()))){

            }else if((transIdList.get("transIDWithGateID").contains(aleLog.getSourceSystemM()))){

            }
        }
    }

    public void getPayloadIc0(AleLog aleLog, Map<String, List<String>> transIdList){

        if(StringUtils.isNullOrEmpty(aleLog.getSourceSystemM())){
            if((transIdList.get("transID").contains(aleLog.getSourceSystemM()))){
            }else if((transIdList.get("transID").contains(aleLog.getSourceSystemM()))){
            }
        }
    }

    public void analyzeLog(String logPath) throws IOException {

        File[] fileList = CsvUtil.getFileList(logPath);
        AleLog aleLog = null;

        Map<String, List<String>> transIdList = YamlUtil.readYamlFile();

        for (int i = 0; i < fileList.length; i++) {
            aleLog = CsvUtil.readExcel(fileList[i].toString());
            if (fileList[i].isFile() && fileList[i].getName().contains(".csv") && fileList[i].getName().contains("f2_0")) {
                getPayloadIc0(aleLog,transIdList);
            }else {
                getPayload(aleLog,transIdList);
            }
        }
    }
}
