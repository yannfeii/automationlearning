package com.psa.xraysealing;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class NMTCase51003 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\XrayScan_Sealing\\51003_NMT_1x40GP_Scan_CIC\\";
    String sample = "src/test/resources/XraySealing/NMT_51003/";

    @Test
    void testCase() throws FileNotFoundException {
        //jsonVerify.generateSampleJson(logRoot+"20220225\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220713\\",sample);
    }
}
