package com.psa.xraysealing;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class NMTCase51002 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\XrayScan_Sealing\\51002\\";
    String sample = "src/test/resources/XraySealing/NMT_51002/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20220228\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220712\\",sample);
    }
}
