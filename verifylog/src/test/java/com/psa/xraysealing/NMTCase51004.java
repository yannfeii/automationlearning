package com.psa.xraysealing;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class NMTCase51004 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\XrayScan_Sealing\\51004\\";
    String sample = "src/test/resources/XraySealing/NMT_51004/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20220228\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220712\\",sample);
    }
}
