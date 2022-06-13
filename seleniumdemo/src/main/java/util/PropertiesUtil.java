package util;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {
    private Properties property;
    private String filePath;
    public PropertiesUtil(String filePath){
        this.filePath = filePath;
        this.property = readProperties();
    }
    private Properties readProperties(){
        Properties prop = new Properties();
        try{
            InputStream inputFile = new FileInputStream(filePath);
            BufferedInputStream propFile = new BufferedInputStream(inputFile);
            prop.load(propFile);
        }catch (IOException e){
            e.printStackTrace();
        }

        return prop;
    }

    public String getProperty(String key) {
        if(property.containsKey(key)){
            String propOut = property.getProperty(key);
            return propOut;
        }else {
            System.out.print("Dont have releated Key");
            return "";
        }
    }
}

