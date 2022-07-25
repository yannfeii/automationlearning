package com.psa.mnlarig;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class EmptyTripTest {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\Manual_Interventions\\MNL_ARIG\\EmptyTrip\\";
    String sample = "src/test/resources/Manual_ARIG/EmptyTrip/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20220401\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220624\\",sample);
    }


}
