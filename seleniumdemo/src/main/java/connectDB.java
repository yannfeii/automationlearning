import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.*;
import java.sql.*;
import java.util.*;

public class connectDB {

    private static void createHeader(String[] headers, SXSSFSheet sheet) {
        SXSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(16F);
        for (int i = 0; i < headers.length; i++) {
            // 创建单元格
            SXSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            sheet.trackAllColumnsForAutoSizing();
            sheet.autoSizeColumn(i);
        }
    }
    private static void createBody(List<List<Object>> dataList, SXSSFSheet sheet) {
        for (int i = 0; i < dataList.size(); i++) {
            // 从第二行开始，第一行做表头
            SXSSFRow row = sheet.createRow(i+1);
            List<Object> rowList = dataList.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                SXSSFCell cell = row.createCell(j);
                XSSFRichTextString text = new XSSFRichTextString(String.valueOf(rowList.get(j)));
                cell.setCellValue(text);
                sheet.trackAllColumnsForAutoSizing();
                sheet.autoSizeColumn(i);
            }
        }
    }

    public static void createSheet(String sheetName , String[] headers , List<List<Object>> dataList , SXSSFWorkbook wb) {
        SXSSFSheet sheet = wb.createSheet(sheetName);
        createHeader(headers, sheet);
        createBody(dataList, sheet);
    }

    public static void export(String sheetName, String[] headers, List<List<Object>> dataList, File destFile) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        createSheet(sheetName, headers, dataList, workbook);
        workbook.write(new FileOutputStream(destFile));
    }
    /*

    public static void main(String[] args) {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //第一步：注册驱动
            //第一种方式：类加载(常用)
            Class.forName("oracle.jdbc.OracleDriver");

            //第二种方式：利用Driver对象
            Driver driver = new OracleDriver();
            DriverManager.deregisterDriver(driver);

            //第二步：获取连接
            //第一种方式：利用DriverManager（常用）
            //connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "你的oracle数据库用户名", "用户名密码");

            //第二种方式：直接使用Driver
            Properties pro = new Properties();
            pro.put("user", "gtosadmin");
            pro.put("password", "gtosadmin");
            connect = driver.connect("jdbc:oracle:thin:@//168.63.235.167:1521/orcl", pro);

            //测试connect正确与否
            System.out.println(connect);


            //第三步：获取执行sql语句对象
            //第一种方式:statement
            //statement = connect.createStatement();

            //第二种方式：PreStatement
            PreparedStatement preState = connect.prepareStatement("SELECT * FROM tos_log WHERE event_id = ?");


            //第四步：执行sql语句
            //第一种方式：
           // resultSet = statement.executeQuery("select  * from tos_log where event_id='TACS-20210719115459985-001'");

            //第二种方式：
            preState.setString(1,"TACS-20210719115459985-001");
            //preState.setInt(1, 2);//1是指sql语句中第一个？,  2是指第一个？的values值
            resultSet = preState.executeQuery();        //执行查询语句
            //查询任何语句，如果有结果集，返回true，没有的话返回false,注意如果是插入一条数据的话，虽然是没有结果集，返回false，但是却能成功的插入一条数据
            //boolean execute = preState.execute();
            //System.out.println(execute);
            String[] headers = new String[]{"EVENT_DT","EVENT_ID","TRANS_ID","MSG_ID","CORRELATION_ID","SOURCE_SYSTEM_M","SOURCE_SYSTEM_ID","MODULE_M","DEST_SYSTEM_M",
                    "DEST_SYSTEM_ID","DURATION_N","IC_ID","LOG_TYPE_M","LOG_LEVEL_M","APPLICATION_M","ENGINE_M","HOST_M","NODE_M","PROCESS_STACK_X","MSG_C","STACK_TRACE_X",
                    "SRC_QUEUE_TOPIC_M","DEST_QUEUE_TOPIC_M","MSG_X","PAYLOAD_X"};
            List<List<Object>> results = new ArrayList<>();
            //第五步：处理结果集
            while (resultSet.next()) {
                List<Object> obj = new ArrayList<>();
                // int id = resultSet.getInt("id");
                String eventDt = resultSet.getString("EVENT_DT");
                obj.add(eventDt);
                String eventId = resultSet.getString("EVENT_ID");
                obj.add(eventId);
                String transId = resultSet.getString("TRANS_ID");
                obj.add(transId);
                String msgId = resultSet.getString("MSG_ID");
                obj.add(msgId);
                String correlationId = resultSet.getString("CORRELATION_ID");
                obj.add(correlationId);
                String sourceM = resultSet.getString("SOURCE_SYSTEM_M");
                obj.add(sourceM);
                String sourceId = resultSet.getString("SOURCE_SYSTEM_ID");
                obj.add(sourceId);
                String moduleM = resultSet.getString("MODULE_M");
                obj.add(moduleM);
                String destM = resultSet.getString("DEST_SYSTEM_M");
                obj.add(destM);
                String destId = resultSet.getString("DEST_SYSTEM_ID");
                obj.add(destId);
                String durationN = resultSet.getString("DURATION_N");
                obj.add(durationN);
                String icId = resultSet.getString("IC_ID");
                obj.add(icId);
                String logtypeM = resultSet.getString("LOG_TYPE_M");
                obj.add(logtypeM);
                String loglevelM = resultSet.getString("LOG_LEVEL_M");
                obj.add(loglevelM);
                String applicationM = resultSet.getString("APPLICATION_M");
                obj.add(applicationM);
                String engineM = resultSet.getString("ENGINE_M");
                obj.add(engineM);
                String hostM = resultSet.getString("HOST_M");
                obj.add(hostM);
                String nodeM = resultSet.getString("NODE_M");
                obj.add(nodeM);
                String processstackX = resultSet.getString("PROCESS_STACK_X");
                obj.add(processstackX);
                String msgC = resultSet.getString("MSG_C");
                obj.add(msgC);
                String stacktraceX = resultSet.getString("STACK_TRACE_X");
                obj.add(stacktraceX);
                String srcqueuetopiM = resultSet.getString("SRC_QUEUE_TOPIC_M");
                obj.add(srcqueuetopiM);
                String destqueuetopicM = resultSet.getString("DEST_QUEUE_TOPIC_M");
                obj.add(destqueuetopicM);
                String msgX = resultSet.getString("MSG_X");
                obj.add(msgX);
                String payloadX = resultSet.getString("PAYLOAD_X");
                obj.add(payloadX);
                results.add(obj);
            }
            File file = new File("C:\\Users\\yanfeit\\Desktop\\test.xlsx");
            export("test", headers, results, file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //第六步：关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     */
}

