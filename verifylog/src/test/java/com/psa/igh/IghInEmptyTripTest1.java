package com.psa.igh;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class IghInEmptyTripTest1 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\IGH\\1_IGH_IN_EmptyTrip\\";
    String sample = "src/test/resources/igh/1_IGH_IN_EmptyTrip/";

    @Test
    void testCase() throws FileNotFoundException {
        //jsonVerify.generateSampleJson(logRoot+"20220401\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220714\\",sample);
    }
}
