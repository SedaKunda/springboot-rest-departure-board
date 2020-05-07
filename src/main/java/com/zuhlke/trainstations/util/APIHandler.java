package com.zuhlke.trainstations.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIHandler {

    public String[] getKey() {
        String APP_ID = "";
        String APP_KEY = "";
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String keyConfigPath = rootPath + "keys.properties";
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(keyConfigPath);
        } catch (FileNotFoundException ex) {
            System.out.println("You need to generate your own API key from TransportAPI.com and add this to a keys.properties file.");
        }
        try {
            prop.load(is);
            APP_ID = prop.getProperty("APP_ID");
            APP_KEY = prop.getProperty("APP_KEY");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new String[]{APP_KEY, APP_ID};
    }
}
