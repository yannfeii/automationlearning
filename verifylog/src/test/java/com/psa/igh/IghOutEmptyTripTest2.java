package com.psa.igh;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class IghOutEmptyTripTest2 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\IGH\\2_IGH_OUT_EmptyTrip\\";
    String sample = "src/test/resources/igh/2_IGH_OUT_EmptyTrip/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20211210\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220714\\",sample);
    }
}
