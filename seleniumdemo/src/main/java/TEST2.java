import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.math.BigDecimal;

public class TEST2 {
    static double num1 = 1586.6;

    static double num2 = 708.75;

    public static Double sub(Double v1,Double v2){

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.subtract(b2).doubleValue();

    }

    public static Double div(Double v1,Double v2){

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.divide(b2,3,BigDecimal.ROUND_HALF_UP).doubleValue();
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        // scale = precision

    }

    public static Double div(Double v1,Double v2,int scale){

        if(scale<0){

            throw new IllegalArgumentException(

                "The scale must be a positive integer or zero");

        }

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    @Test
    public static void TestDir(File dir){
        if(dir.length()<=0) {
            dir = new File("D:\\SelfStudy");

        }else{
            if(dir.isFile() && dir.getName().endsWith(".exe")){
                System.out.println(dir);
            }else if (dir.isDirectory()){
                File[] fileList = dir.listFiles();
                for (File file:fileList) {
                    TestDir(file);
                }
            }
        }



    }

    public static Double mul(Double v1,Double v2){

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.multiply(b2).doubleValue();

    }
    public static void main(String[] args) {

        Double result = div(num1, num2);
        System.out.println(result);
    }
}
