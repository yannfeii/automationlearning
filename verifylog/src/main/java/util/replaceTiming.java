package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class replaceTiming {

    public static  void main(String[] args) {


        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter mmddhhFormat = DateTimeFormatter.ofPattern("MMddHHmm");
        DateTimeFormatter hhmmssFormat = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        DateTimeFormatter yyyymmddFormat = DateTimeFormatter.ofPattern("YYYYMMdd");
        DateTimeFormatter sampleFormat = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
        DateTimeFormatter fullFormat = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS");
        String mmddhh = dateTime.format(mmddhhFormat);
        String hhmmss = dateTime.format(hhmmssFormat);
        String date = dateTime.format(dateFormat);
        String yyyymmdd = dateTime.format(yyyymmddFormat);
        String sampleDate = dateTime.format(sampleFormat);
        String fullDate = dateTime.format(fullFormat);

        try {
//            FileReader file = new FileReader("C:\\Users\\yanfeit\\Desktop\\TEMMMP\\EXPORT_1x40GP_Scanning\\PREGATE_DOCU.txt");
//            StringBuilder stringBuilder = new StringBuilder();
//            BufferedReader reader = new BufferedReader(file);
//            String s = null;
//            while(( s = reader.readLine())!=null){
//                stringBuilder.append(s);
//            }
//            reader.close();
//            System.out.println(stringBuilder.toString());
//            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
//            String eventID = jsonObject.getJSONObject("gtosplus_ops_header").getString("event_id");
//            String eventDT = jsonObject.getJSONObject("gtosplus_ops_header").getString("event_dt");
//
//            if (StringUtils.isNotEmpty(eventID)) {
//                String eventIdNew = StringUtils.substringBeforeLast(eventID,"-")+"-"+seconds;
//                System.out.println(eventIdNew);
//                jsonObject.getJSONObject("gtosplus_ops_header").put("event_id",eventIdNew);
//            }
//            Writer writer = jsonObject.write(new FileWriter("C:\\Users\\yanfeit\\Desktop\\TEMMMP\\EXPORT_1x40GP_Scanning\\PREGATE_DOCU.txt"),4,0);
//            writer.flush();
//            writer.close();





            FileReader payload = new FileReader("D:\\WorkFiles\\02-PSA\\04-FunctionTest\\MNL_ARIG\\1x40RF\\MNL_ARIG_IPM807.txt");
            JSONReader jsonPayload = new JSONReader(payload,Feature.OrderedField);
            jsonPayload.config(Feature.OrderedField,true);
            JSONObject jsonObject = jsonPayload.readObject(JSONObject.class);
            jsonPayload.close();
            JSONObject headerObject = jsonObject.getJSONObject("gtosplus_ops_header");
            String eventID = headerObject.getString("event_id");
            JSONObject bodyObject = jsonObject.getJSONObject("gtosplus_ops_body");
            JSONArray compJsonArr = bodyObject.getJSONArray("cntr_info");
            String eventIdNew;
            String compDT;

            if (StringUtils.isNotEmpty(eventID)) {
                eventIdNew = StringUtils.substringBeforeLast(eventID,"-")+"-"+mmddhh;
                System.out.println(eventIdNew);
                jsonObject.getJSONObject("gtosplus_ops_header").put("event_id",eventIdNew);
            }

            if (StringUtils.isNotEmpty(headerObject.getString("event_dt"))) {
                System.out.println("--eventDTNew--"+fullDate);
                headerObject.put("event_dt",fullDate);
            }

            if (StringUtils.isNotEmpty(bodyObject.getString("pg_dt"))) {
                if(StringUtils.contains((headerObject.getString("trans_id")),"HT_PREGATE")){
                    bodyObject.put("pg_dt",fullDate);
                } else {
                    bodyObject.put("pg_dt",sampleDate);
                }
            }

            if (StringUtils.isNotEmpty(bodyObject.getString("date_arrived"))) {
                bodyObject.put("date_arrived",yyyymmdd);
            }

            if (StringUtils.isNotEmpty(bodyObject.getString("date_left"))) {
                bodyObject.put("date_left",yyyymmdd);
            }

            if (StringUtils.isNotEmpty(bodyObject.getString("time_arrived"))) {
                bodyObject.put("time_arrived",hhmmss);
            }

            if (StringUtils.isNotEmpty(bodyObject.getString("time_left"))) {
                bodyObject.put("time_left",hhmmss);
            }

            if(!Objects.isNull(compJsonArr)) {
                for (int i=0; i< compJsonArr.size();i++) {
                    JSONObject arr = (JSONObject) compJsonArr.get(i);
                    compDT = arr.getString("completion_dt");
                    if (StringUtils.isNotEmpty(compDT)) {
                        System.out.println("--compDtNew--"+fullDate);
                        arr.put("completion_dt",fullDate);
                    }

                }
            }

            FileWriter payloadWriter = new FileWriter("D:\\WorkFiles\\02-PSA\\04-FunctionTest\\MNL_ARIG\\1x40RF\\MNL_ARIG_IPM807.txt");
            JSONWriter jsonWriter = new JSONWriter(payloadWriter);
            jsonWriter.config(SerializerFeature.PrettyFormat,true);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
