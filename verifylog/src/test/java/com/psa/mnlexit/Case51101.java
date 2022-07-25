package com.psa.mnlexit;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class Case51101 {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\Manual_Interventions\\51101\\";
    String sample = "src/test/resources/MNL_EXIT/51101/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20220221\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220620\\",sample);
    }

}
