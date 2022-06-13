package util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TestFile {

    public static void main(String[] a){
//        try {
//            Runtime rt = Runtime.getRuntime();
////Process pr = rt.exec("cmd /c start D:\\WorkFiles\\02-PSA\\07-GETLOG\\SIT_DB_IC1.cmd D:\\WorkFiles\\02-PSA\\07-GETLOG\\data.txt");
//            Process pr = rt.exec("D:\\WorkFiles\\02-PSA\\04-FunctionTest\\Internal_Test\\5.10_X-Ray_Customs_Sealing\\51001\\TEST.bat");
//
//            FileWriter fw = new FileWriter("D:/Buffered.txt");
//            BufferedWriter bufw = new BufferedWriter(fw);
//            bufw.write("hello world");
//            bufw.newLine(); //这是BufferedWrite特有的方法 作用是换行起到了分割的作用
//            bufw.write("!hello world");
//            bufw.flush();
//            bufw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("asda2021-12-23".indexOf("2021-"));
        System.out.println("asda2021-06-14 12:53:10.000 ".substring(4,27));
        formatDate();
    }

    public static void formatDate(){
        try {
            FileReader fd = new FileReader("D:\\WorkFiles\\02-PSA\\04-FunctionTest\\Internal_Test\\5.10_X-Ray_Customs_Sealing\\51001\\51001_GL_20210817_DB.txt");
            BufferedReader br = new BufferedReader(fd);
            FileWriter fw = new FileWriter("D:/Buffered.txt");
            BufferedWriter bufw = new BufferedWriter(fw);
            String line = null;
            while (Objects.nonNull(line = br.readLine())){
                while (line.indexOf("2021-")>0){
                    int index = line.indexOf("2021-");
                    String date = line.substring(index,index+23);
                    SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
                    SimpleDateFormat sim2 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss.SSS aa", Locale.ENGLISH);
                    if(StringUtils.isNotEmpty(date)){
                        Date d = sim1.parse(date);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(d);
                        calendar.add(Calendar.HOUR,-8);

                        String newDate = sim2.format(calendar.getTime());
                        line = line.replaceAll(date,newDate);
                    }
                }
                bufw.write(line);
                bufw.newLine();
            };
            bufw.flush();
            bufw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
