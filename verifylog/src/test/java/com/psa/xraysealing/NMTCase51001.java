package com.psa.xraysealing;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class NMTCase51001 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\XrayScan_Sealing\\51001_NMT\\";
    String sample = "src/test/resources/XraySealing/NMT_51001/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20220228\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220712\\",sample);
    }
}
