package com.psa.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.psa.entity.AleLog;

import java.io.File;
import java.io.IOException;

public class CsvUtil {

    public static File[] getFileList(String logPath) {

        File filePath = new File(logPath);
        File[] fileList = filePath.listFiles();
        return fileList;

    }

    public static AleLog readExcel(String pathName) throws IOException {

        String fileType = pathName.substring(pathName.lastIndexOf(".")+1,pathName.length());

        AleLog aleLog = null;
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema logListSchema = CsvSchema.emptySchema().withHeader();
        csvMapper.findAndRegisterModules();
        MappingIterator<AleLog> logList = csvMapper.readerFor(AleLog.class)
                .with(logListSchema)
                .readValues(new File(pathName));
        while (logList.hasNext()){
            aleLog = logList.next();
        }

        return aleLog;
    }


}
