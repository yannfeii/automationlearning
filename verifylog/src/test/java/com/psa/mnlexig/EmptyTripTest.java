package com.psa.mnlexig;

import org.junit.jupiter.api.Test;
import util.JsonVerify;

import java.io.FileNotFoundException;

public class EmptyTripTest {

    JsonVerify jsonVerify;

    String logRoot = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\Manual_Interventions\\MNL_EXIG\\EmptyTrip\\";
    String sample = "src/test/resources/MNL_EXIG/EmptyTrip/";

    @Test
    void testCase() throws FileNotFoundException {
        jsonVerify.generateSampleJson(logRoot+"20220331\\",sample);
        jsonVerify.readJsonFile(logRoot+"20220617\\",sample);
    }


}
