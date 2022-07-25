package com.sun.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWithIgnoringCase;
import static org.junit.Assert.assertTrue;

public class ExcelTest {

    @Test
    void excelTest(){

        try {
            String pathName = "aa.xlsx";
            String fileType = pathName.substring(pathName.lastIndexOf(".")+1,pathName.length());
            assertThat("Is not excel",fileType,anyOf(endsWithIgnoringCase("xlsx"),endsWithIgnoringCase("xls")));

            File file = new File(pathName);
            assertTrue("文件不存在", file.exists());

            FileInputStream stream = new FileInputStream(file);
            Workbook workbook = null;

            if(fileType.equalsIgnoreCase("xls")){
                workbook = new HSSFWorkbook();

            }else if(fileType.equalsIgnoreCase("xlsx")){
                workbook = new XSSFWorkbook();
            }

            Sheet sheet = workbook.getSheetAt(0);
            int totalSheets = workbook.getNumberOfSheets();
            int totalRows = sheet.getPhysicalNumberOfRows();

            Map<Integer, List<Object>> data = new HashMap<>();

            int i=0;

            for(Row row:sheet){
                data.put(i,new ArrayList<>());
                System.out.println("getPhysicalNumberOfCells ->> "+row.getPhysicalNumberOfCells());

                for(Cell cell:row){
                    List<Object> objects = data.get(i);
                    CellType cellType = cell.getCellType();
                    switch(cellType){
                        case STRING:
                            objects.add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:

                            if(DateUtil.isCellDateFormatted(cell)){
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                objects.add(format.format(cell.getDateCellValue()));
                            }else{
                                objects.add(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            objects.add(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            objects.add(cell.getCellFormula());
                            break;
                        default: objects.add(" ");
                    }

                }
                i++;
            }
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
