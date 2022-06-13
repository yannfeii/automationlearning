package util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelUtils {

    public static List<Map<String,Object>> getList(String filePath) {
        try {
            List<Map<String,Object>> list = new ArrayList();
            FileInputStream fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(fileInputStream);//poi
            //2.获取sheet 从0开始
            Sheet sheet = hssfWorkbook.getSheetAt(0);
            Row header = sheet.getRow(0);
            List<String> headerList = new ArrayList<String>();
            for (int cellNum = 0; cellNum < header.getLastCellNum(); cellNum++) {
                Cell cell = header.getCell(cellNum);//获取单元格列对象
                headerList.add(cell.getStringCellValue());
            }
            if(null != headerList && headerList.size()>0) {
                //循环所有行getLastRowNum指的是sheet表里的最后一行
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);//获取行对象
                    Map<String, Object> map = new HashMap();
                    //循环每行中的所有单元格
                    for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                        Cell cell = row.getCell(cellNum);//获取单元格列对象
                        switch(cell.getCellType()) {
                            case STRING:
                                String value = cell.getStringCellValue();    //得到单元格值
                                map.put(headerList.get(cellNum), cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                double  number = cell.getNumericCellValue(); //得到单元格内数字
                                map.put(headerList.get(cellNum), cell.getNumericCellValue());
                                break;
                            default:
                                System.out.println("---");
                        }
                    }
                    list.add(map);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] a) throws Exception {
        List<Map<String,Object>> list = ExcelUtils.getList("C:\\Users\\yanfeit\\Desktop\\excelTest.xlsx");
        list.forEach(item->{
            System.out.println(item.toString());
        });
        File file = new File("C:\\Users\\yanfeit\\Desktop\\test.xlsx");
        List<List<Object>> res = list.stream().map(map->{
            return map.values().stream().collect(Collectors.toList());
        }).collect(Collectors.toList());
        String[] headers = new String[]{"event_id","wrw","test"};
        export("test",headers,res,file);
    }



    public static void export(String sheetName, String[] headers, List<List<Object>> dataList, File destFile) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        createSheet(sheetName, headers, dataList, workbook);
        workbook.write(new FileOutputStream(destFile));
    }


    /**
     * description: 创建sheet表格
     * @param sheetName  表sheet 名字
     * @param headers  表头
     * @param dataList 表数据
     * @param wb
     * @return void
     * @version v1.0
     * @author w
     * @date 2020年3月30日 下午2:33:39
     */
    public static void createSheet(String sheetName , String[] headers , List<List<Object>> dataList , SXSSFWorkbook wb) {
        SXSSFSheet sheet = wb.createSheet(sheetName);
        // 设置表头和单元格格式
        CellStyle headStyle = setHeaderStyle(wb);
        CellStyle bodyStyle = setBodyStyle(wb);
        // 创建表头和单元格数据
        createHeader(headers, sheet, headStyle);
        createBody(dataList, sheet, bodyStyle);
    }

    /**
     * description: 创建表头
     * @param headers
     * @param sheet
     * @param headStyle
     * @return void
     * @version v1.0
     * @author w
     * @date 2020年3月30日 下午3:03
     */
    private static void createHeader(String[] headers, SXSSFSheet sheet, CellStyle headStyle) {
        SXSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(16F);
        for (int i = 0; i < headers.length; i++) {
            // 创建单元格
            SXSSFCell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            sheet.trackAllColumnsForAutoSizing();
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * description: 表格中填充数据
     * @param dataList
     * @param sheet
     * @param bodyStyle
     * @return void
     * @version v1.0
     * @author w
     * @date  2020年3月30日 下午3:13
     */
    private static void createBody(List<List<Object>> dataList, SXSSFSheet sheet, CellStyle bodyStyle) {
        for (int i = 0; i < dataList.size(); i++) {
            // 从第二行开始，第一行做表头
            SXSSFRow row = sheet.createRow(i+1);
            List<Object> rowList = dataList.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                SXSSFCell cell = row.createCell(j);
                cell.setCellStyle(bodyStyle);
                XSSFRichTextString text = new XSSFRichTextString(String.valueOf(rowList.get(j)));
                cell.setCellValue(text);
                sheet.trackAllColumnsForAutoSizing();
                sheet.autoSizeColumn(i);
            }
        }
    }

    /**
     * description: 设置单元格内容样式
     * @param wb
     * @return HSSFCellStyle
     * @version v1.0
     * @author w
     * @date 2020年3月30日 下午2:42:39
     */
    private static CellStyle setBodyStyle(SXSSFWorkbook wb) {
        // 设置表格单元格格式
        CellStyle style = wb.createCellStyle();
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//        style.setBorderTop(BorderStyle.THIN);
//        style.setAlignment(HorizontalAlignment.LEFT);

        // 设置字体格式
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        style.setFont(font);
        return style;
    }

    /**
     * description: 设置表头样式
     * @param wb
     * @return
     * @return HSSFCellStyle
     * @version v1.0
     * @author w
     * @date 2020年3月30日 下午2:38:39
     */
    private static CellStyle setHeaderStyle(SXSSFWorkbook wb) {
        // 设置表格单元格格式
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());

        // 设置字体格式
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        return style;
    }


}
