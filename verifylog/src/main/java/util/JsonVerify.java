package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonVerify {

    static JSONObject jsonObject;

    static List<String> transID = new ArrayList<>();
    static List<String> transIDWithGateID = new ArrayList<>();
    static List<String> transIDForIC0 = new ArrayList<>();

    static File[] sampleList;
    static Logger log = Logger.getLogger(JsonVerify.class);

    static int PMST = 0;
    static int PMARIV = 0;
    static int JOB_COMP = 0;

    static {

        transIDWithGateID.add("SST_INFO");
        transIDWithGateID.add("BARR_INFO");
        transIDWithGateID.add("GATE_LANE_UPD");
        transIDWithGateID.add("CNSL_MI");
        transIDWithGateID.add("PREGATE_UPD");
        transIDWithGateID.add("cntr_customs");

        transIDForIC0.add("PMARIV");
        transIDForIC0.add("PMEXIT");
        transIDForIC0.add("HLR_HLRS");
        transIDForIC0.add("IGH_PREGATE");

        transID.add("PMARIV");
        transID.add("JOB_INSTR");
        transID.add("CNSL_MI");
        transID.add("JOB_COMP");
        transID.add("SST_INFO");
        transID.add("BARR_INFO");
        transID.add("GATE_LANE_UPD");
        transID.add("HLR_HLRS");
        transID.add("CUSTOMS_STATUS");
        transID.add("PMEXIT");
        transID.add("CNTR_EXIT");
        transID.add("PREGATE_UPD");
        transID.add("CNSL_JOB_COMPL_R");
        transID.add("VEH_CUSTOMS_ARIV");
        transID.add("VEH_U_TURN_LIST_R");
        transID.add("cntr_customs");
        transID.add("CNTR_HOUSEKEEP");
        transID.add("IGH_PREGATE");
        transID.add("HT_OUT_GATE");
        transID.add("HT_IN_GATE");
    }


    public static void readJsonFile(String logPath, String samplePathRoot) throws FileNotFoundException {

        File filePath = new File(logPath);
        File[] fileList = filePath.listFiles();

        File sampleName = new File(samplePathRoot);
        sampleList = sampleName.listFiles();

        PMST = 1;
        PMARIV = 1;
        JOB_COMP = 1;
        Set verifiedSample = new HashSet();

        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile() && fileList[i].getName().contains(".json") && !fileList[i].getName().contains(".csv")) {

                JSONReader jsonReader = new JSONReader(new FileReader(logPath + fileList[i].getName()));

                String jsonStr = fileString(logPath + fileList[i].getName());

                jsonReader.startArray();

                while (jsonReader.hasNext()) {

                    String jsonString = jsonReader.readString();
                    jsonObject = JSONObject.parseObject(jsonString);
                    String fileName = fileList[i].getName();
                    String payloadString = jsonObject.getString("payload_x");
                    String transid = jsonObject.getString("trans_id");
                    String destTopic = jsonObject.getString("dest_queue_topic_m");
                    String srcTopic = jsonObject.getString("src_queue_topic_m");

                    String samplePath = sampleFileName(payloadString,destTopic,fileName,jsonStr,samplePathRoot,logPath);

                    if (StringUtils.isNotEmpty(samplePath)) {
                        verifiedSample.add(StringUtils.substringAfterLast(samplePath,"\\"));
                        if (StringUtils.contains(samplePath, "HLR_HLRS") || StringUtils.contains(samplePath, "IC0_OEF")) {
                            checkJsonForIC0(transid, payloadString, samplePath, fileName);
                        } else {
                            compareJosnWithPayload(jsonObject, samplePath, fileName);
                        }

                    }
                }
            }
        }
        System.out.println("------------------------------------");
        System.out.println("sampleList---------"+sampleList.length);
        System.out.println("verifiedSample---------"+verifiedSample.size());

//        if(sampleList.length != verifiedSample.size()){
//            for (File sample:sampleList) {
//                String sampleTmp = StringUtils.substringAfterLast(sample.toString(),"\\");
//                if(!verifiedSample.contains(sampleTmp)){
//                    System.out.println("sampleList---------"+sampleTmp);
//                    String jsonStr = fileString(sample.toString());
//                    System.out.println("sampleList payload---------"+jsonStr);
//                }
//            }
//        }

    }

    public static String fileString(String fileLoc) {

        File file = new File(fileLoc);

        String jsonStr = "";

        try {
            jsonStr = new String(Files.readAllBytes(Paths.get(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static void compareJosnWithPayload(JSONObject jsonObject, String samplePath, String fileName) {


        String resultStr = null;
        String transid = jsonObject.getString("trans_id");

        if (transID.contains(transid) && ("START").equals(jsonObject.getString("log_type_m"))
                && ("GMS").equals(jsonObject.getString("source_system_m"))) {

            String payloadString = jsonObject.getString("payload_x");

            try {
                JSONReader sampleReader = new JSONReader(new FileReader(samplePath));

                if (StringUtils.containsAny(transid, "PMARIV", "PMEXIT", "CNTR_EXIT", "CNSL_MI", "JOB_INSTR")) {
                    resultStr = new CompareJson("cntr_n", "gtosplus_ops_header,arrival_dt,exit_dt,opr_logon,job_id,pm_n,img_path,img_path_m,mount_dt").compareJson(sampleReader.readString(), payloadString);
                }else if(StringUtils.containsAny(transid, "HT_IN_GATE","HT_OUT_GATE")){
                    resultStr = new CompareJson("trailer_n", "gtosplus_ops_header").compareJson(sampleReader.readString(), payloadString);
                } else {
                    resultStr = new CompareJson("", "gtosplus_ops_header,event_dt,console_id,pregate_id,pm_n").compareJson(sampleReader.readString(), payloadString);
                }
                boolean isjson = isJson(resultStr);

                if(isjson) {
                    JSONObject jsonObjectTmp = JSON.parseObject(resultStr, Feature.OrderedField);
                    resultStr = JSON.toJSONString(jsonObjectTmp, SerializerFeature.PrettyFormat);
                }
                System.out.println(" ");
                System.out.println(StringUtils.substringAfterLast(samplePath, "\\") + "---------" + fileName);
                if (resultStr.length() > 4) {
                    System.out.println(jsonObject.getString("trans_id") + "--------");
                    System.out.println(resultStr);
                } else {
                    System.out.println(jsonObject.getString("trans_id") + "-------- same");
                }

            } catch (FileNotFoundException e) {
                log.error("*------" + transid + "------- json sample cannot to be found");
            }
        }

    }


    public static void checkJsonForIC0(String transId, String payload, String samplePath, String fileName) {


        FileReader sampleReader = null;
        if (StringUtils.isNotEmpty(payload)) {
            try {

                sampleReader = new FileReader(samplePath);
                BufferedReader bufferedReader = new BufferedReader(sampleReader);
                StringBuilder sampleSB = new StringBuilder();
                String words = null;

                while ((words = bufferedReader.readLine()) != null) {
                    sampleSB.append(words);
                }

                String sample = sampleSB.toString();

                String[] splitMegS = null;
                String[] splitMegP = null;

                if (("HLR_HLRS").equals(transId)) {
                    payload = StringUtils.substringAfter(payload, "|");
                    sample = StringUtils.substringAfter(sample, "|");
                    sample = StringUtils.substringBefore(sample, "\"");
                    splitMegS = spiltMes(sample);
                    splitMegP = spiltMes(payload);
                }

                if (("PMARIV").equals(transId) || ("PMEXIT").equals(transId)) {
                    payload = payload.substring(98).trim();
                    sample = StringUtils.substringBeforeLast(sample, "\"");
                    sample = sample.substring(98).trim();
                }

                System.out.println(" ");
                System.out.println(StringUtils.substringAfterLast(samplePath, "\\") + "-----------" + fileName);

                if (StringUtils.isNotEmpty(payload)) {
                    if (StringUtils.equals(payload, sample) || (splitMegS!=null && splitMegP!=null && StringUtils.equals(Arrays.toString(splitMegP), Arrays.toString(splitMegS)))) {
                        System.out.println(transId + "------------ Same");
                    } else {
                        System.out.println(transId + "----------- Error, payload is not the same as the sample");
                        System.out.println("payload: ");
                        System.out.println(payload);
                        System.out.println("sample: ");
                        System.out.println(sample);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] spiltMes(String meg){

        String[] splitMes = null;
        if(StringUtils.contains(meg,"]")){
            splitMes = StringUtils.split(meg,"[");
            Arrays.sort(splitMes);
        } else if(StringUtils.contains(meg,"|")){
            splitMes = StringUtils.split(meg,"|");
            Arrays.sort(splitMes);
        }
        return splitMes;
    }
    public static void generateSampleJson(String logPath, String samplePathRoot) throws FileNotFoundException {

        File filePath = new File(logPath);
        File[] fileList = filePath.listFiles();
        PMST = 1;
        PMARIV = 1;
        JOB_COMP = 1;

        for (int i = 0; i < fileList.length; i++) {

            String fileName = fileList[i].getName();

            if (fileList[i].isFile() && fileName.contains(".json") && !fileName.contains(".csv")) {

                JSONReader jsonReader = new JSONReader(new FileReader(logPath + fileList[i].getName()));

                String jsonStr = "";
                File file = new File(logPath + fileList[i].getName());
                try {
                    jsonStr = new String(Files.readAllBytes(Paths.get(file.getPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                jsonReader.startArray();

                while (jsonReader.hasNext()) {

                    String jsonString = jsonReader.readString();
                    jsonObject = JSONObject.parseObject(jsonString);
                    String payloadString = jsonObject.getString("payload_x");
                    String destTopic = jsonObject.getString("dest_queue_topic_m");

                    String samplePath =sampleFileName(payloadString,destTopic,fileName,jsonStr,samplePathRoot,logPath);

//                    File sampleSrc = new File(samplePathRoot);
//                    if(sampleSrc.exists()){
//                        sampleSrc.delete();
//                    }else {
//
//                    }
                    if (StringUtils.isNotEmpty(samplePath)) {
                        createSampleFile(samplePath, payloadString);
                    }
                }
            }
        }
    }

    public static boolean isJson(String str){
        try {
            JSONObject jsonStr= JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void createSampleFile(String samplePath, String payloadString) {

        BufferedWriter writer = null;
        String jsonData;
        boolean isjson = isJson(payloadString);

        if(isjson) {
            JSONObject jsonObject = JSON.parseObject(payloadString, Feature.OrderedField);
            jsonData = JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat);
        }else {
            jsonData = payloadString;
        }

//        String jsonData = JSON.toJSONString(payloadString, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteDateUseDateFormat);

//        if (samplePath.contains("json")) {
//            jsonData = JSON.toJSONString(payloadString);
//        }

        File file = new File(samplePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String sampleJsonForPMARIVandPMEXIT(String transId, String samplePath) {

        String sampleJson = "";

        if (("PMARIV").equals(transId) || ("PMEXIT").equals(transId)) {

            if (StringUtils.isNotEmpty(jsonObject.getString("dest_queue_topic_m"))) {

                if (("tos.gos.gms.gate.f2.fr.gtosp.gate.gw.ut.q").equals(jsonObject.getString("dest_queue_topic_m"))) {
                    sampleJson = samplePath + transId + "_IC0_" + "TOS" + ".txt";
                } else if (("pn.pnhlr.pg.tuas.fr.gtosp.ops.gw.tuas.q").equals(jsonObject.getString("dest_queue_topic_m"))) {
                    sampleJson = samplePath + transId + "_IC0_" + "PN" + ".txt";
                } else if (("oss.ctis.oef.recv.ut.fr.gtosp.ops.gw.ut.q").equals(jsonObject.getString("dest_queue_topic_m"))) {
                    sampleJson = samplePath + transId + "_IC0_" + "OEF" + ".txt";
                }
            }
        }

        return sampleJson;
    }

    public static String sampleFileNameIC1(String payloadString,String transid, String samplePathRoot){

        String samplePath = "";
        String gateLaneID = "";

        if (("START").equals(jsonObject.getString("log_type_m"))) {

            if ((transID.contains(transid)) && ("GMS").equals(jsonObject.getString("source_system_m"))) {

                if (transIDWithGateID.contains(transid)) {

                    if (StringUtils.contains(payloadString,"\"gate_lane_id\":1702800007") || StringUtils.contains(payloadString,"\"lane_n\":12")) {
                        gateLaneID = "1702800007";
                    } else if (StringUtils.contains(payloadString,"\"gate_lane_id\":1702800011") || StringUtils.contains(payloadString,"\"lane_n\":16")) {
                        gateLaneID = "1702800011";
                    } else if (StringUtils.contains(payloadString,"\"gate_lane_id\":1702800020")|| StringUtils.contains(payloadString,"\"lane_n\":2")) {
                        gateLaneID = "1702800020";
                    }

                    if(StringUtils.isNotEmpty(gateLaneID)) {
                        samplePath = samplePathRoot + transid + "_" + gateLaneID + ".json";

                        if (("SST_INFO").equals(transid)) {
                            if (StringUtils.contains(payloadString, "\"004\"")) {
                                samplePath = samplePathRoot + transid + "_" + gateLaneID + "_004.json";
                            }
                        }
                    }

                } else {
                    samplePath = samplePathRoot + transid + ".json";
                }
            }
        }
        return samplePath;
    }

    public static String sampleFileName(String payloadString,String destTopic,String fileName, String jsonStr,String samplePathRoot,String logPath) throws FileNotFoundException {

        String samplePath = "";

        String transId = jsonObject.getString("trans_id");

        if (fileName.contains("f2_0")) {

            if (transIDForIC0.contains(transId)) {

                samplePath = sampleJsonForPMARIVandPMEXIT(transId,samplePathRoot);

                if ("HLR_HLRS".equals(transId)&&
                        StringUtils.contains(destTopic,"citos.chi.router.fr.citospnsg.q")) {

                    String fileStrigIC2 = fileString(logPath+fileName.replace("f2_0","f2_2"));

                    if (StringUtils.contains(jsonStr, "PMARIV")) {
                        samplePath = samplePathRoot + transId + "_IC0_PMARIV_" + PMARIV + ".txt";
                        PMARIV++;
                    } else if (StringUtils.contains(jsonStr, "PMEXIT")) {
                        samplePath = samplePathRoot + transId + "_IC0_PMEXIT" + ".txt";
                    } else if (StringUtils.contains(fileStrigIC2, "JOB_COMP")) {
                        samplePath = samplePathRoot + transId + "_IC0_JOB_COMP_" + JOB_COMP + ".txt";
                        JOB_COMP++;
                    } else if (StringUtils.contains(fileStrigIC2, "CUSTOMS_STATUS") && StringUtils.contains(fileStrigIC2,"CUSTOMS_COMP")) {
                        samplePath = samplePathRoot + transId + "_IC0_PMST_" + PMST + ".txt";
                        PMST++;
                    } else {
                        samplePath = samplePathRoot + transId + "_IC0" + ".txt";
                    }
                }else if(StringUtils.contains(destTopic,"tos.gos.gms.gate.f2.fr.gtosp.gate.gw.ut.q")){
                    samplePath = samplePathRoot + transId + "_IC0" + ".txt";
                }else if(StringUtils.contains(destTopic,"MQ_GTOSPLUS_OPS_V1_GATE_TO_NGOS_FRGTOS")){
                    samplePath = samplePathRoot + transId + "_IC0" + ".txt";
                }
            }
        } else {
            samplePath = sampleFileNameIC1(payloadString,transId,samplePathRoot);
        }
        return samplePath;
    }


}
