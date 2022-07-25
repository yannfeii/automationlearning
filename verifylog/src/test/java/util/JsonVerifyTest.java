package util;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class JsonVerifyTest {

    JsonVerify jsonVerify;

    String logPath;
    String logPath1;
    String samplePath;

    @Test
    void EXPORT_1x40RF()throws FileNotFoundException {
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\EXPORT_1x40RF\\20220112\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\EXPORT_1x40RF\\20220403\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\EXPORT\\EXPORT_1x40RF\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void EXPORT_2x20RF_MI()throws FileNotFoundException {
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\EXPORT_2x20RF_MI\\20220303\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\EXPORT_2x20RF_MI\\20220402\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\EXPORT\\EXPORT_2x20RF_MI\\";
        jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void EXPORT_2x20RF_Scanning() throws FileNotFoundException {
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\EXPORT_2x20RF_Scanning\\20220216\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\EXPORT_2x20RF_Scanning\\20220331\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\EXPORT\\EXPORT_2x20RF_Scanning\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void IMPORT_1x40RF()throws FileNotFoundException {
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\IMPORT_1x40RF\\20220215\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\IMPORT_1x40RF\\20220403\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\IMPORT\\IMPORT_1x40RF\\";
        jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket3_1_Hybrid_2x40GP_45Foot() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid1_2x40GP_45Foot\\20220324\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid1_2x40GP_45Foot\\20220510\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket3\\1_Hybrid_2x40GP_45Foot\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket3_2_Hybrid_4x20RF() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid_4x20RF\\20220324\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid2_4x20RF\\20220511\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket3\\2_Hybrid_4x20RF\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket3_3_Hybrid_4x20RF_MI() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid3_4x20RF_MI\\20220316\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid3_4x20RF_MI\\20220510\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket3\\3_Hybrid_4x20RF_MI\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket3_4_Hybrid_4x20RF() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid_4x20RF_Scanning_Sealing\\20220324\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid4_4x20RF_Scanning_Sealing\\20220509\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket3\\4_Hybrid_4x20RF_Scanning_Sealing\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket3_5_Hybrid_2x40RF() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid5_2x40RF_ExpScanning_ImpSealing_MI\\20220317\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid5_2x40RF_ExpScanning_ImpSealing_MI\\20220510\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket3\\5_Hybrid_2x40RF_ExpScanning_ImpSealing_MI\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket3_6_Hybrid_4x20RF_MI() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid6_4x20RF_ExpScanning_ImpSealing_MI\\20220304\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket3\\Hybrid6_4x20RF_ExpScanning_ImpSealing_MI\\20220510\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket3\\6_Hybrid_4x20RF_ExpScanning_ImpSealing_MI\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket4_1A_2x20RF_Export() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\1_Auto-Reject_Verified\\2x20RF_Export\\20220317\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\1_Auto-Reject_Verified\\2x20RF_Export\\20220401\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket4\\1_Auto-Reject_Verified\\2x20RF_Export\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket4_2A_Export_1x40RF() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\2_Auto-Reject_KIV\\Export_1x40RF\\20220325\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\2_Auto-Reject_KIV\\Export_1x40RF\\20220401\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket4\\2_Auto-Reject_KIV\\Export_1x40RF\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket4_3A_2x20RF_Import() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\3_Manual-Reject_Arrival\\2x20RF_Import\\20220317\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\3_Manual-Reject_Arrival\\2x20RF_Import\\20220401\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket4\\3_Manual-Reject_Arrival\\2x20RF_Import\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket4_4A_Import_2x20RF() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\4_Manual-Reject_Exit\\Import_2x20RF\\20220325\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\4_Manual-Reject_Exit\\Import_2x20RF\\20220401\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket4\\4_Manual-Reject_Exit\\Import_2x20RF\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void Bucket4_4B_Hybrid_4x20RF() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\4_Manual-Reject_Exit\\Hybrid_4x20RF\\20220317\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\Bucket4\\4_Manual-Reject_Exit\\Hybrid_4x20RF\\20220401\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\Bucket4\\4_Manual-Reject_Exit\\Hybrid_4x20RF\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void NMT4_2x20GP_2NMT_1Red_1Green() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\NMT\\NMT4_2x20GP_2NMT_1Red_1Green\\20220228\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\NMT\\NMT4_2x20GP_2NMT_1Red_1Green\\20220511\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\NMT\\NMT4_2x20GP_2NMT_1Red_1Green\\";
        // jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void NMT6_1x40GP_noNMTinYard() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\NMT\\NMT6_1x40GP_noNMTinYard\\20220314\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\NMT\\NMT6_1x40GP_noNMTinYard\\20220511\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\NMT\\NMT6_1x40GP_noNMTinYard\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }

    @Test
    void NMT7_2x20GP_1AbortDelivery_MTLane() throws FileNotFoundException{
        logPath = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\NMT\\NMT7_2x20GP_1AbortDelivery_MTLane\\20220318\\";
        logPath1 = "D:\\WorkFiles\\02-PSA\\04-FunctionTest\\RegressionTest\\NMT\\NMT7_2x20GP_1AbortDelivery_MTLane\\20220512\\";
        samplePath = "D:\\WorkFiles\\SourceCode\\PSA\\VerifyLog\\src\\test\\resources\\NMT\\NMT7_2x20GP_1AbortDelivery_MTLane\\";
        //jsonVerify.generateSampleJson(logPath,samplePath);
        jsonVerify.readJsonFile(logPath1,samplePath);
    }
}