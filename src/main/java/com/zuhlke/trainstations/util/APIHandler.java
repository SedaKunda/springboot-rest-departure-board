package com.zuhlke.trainstations.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class APIHandler {
    public String[] getKeysFile() {
        String APP_ID = "";
        String APP_KEY = "";
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("keys.properties")).getFile());
            properties.load(new FileInputStream(file.getAbsolutePath()));
             APP_ID = properties.getProperty("APP_ID");
             APP_KEY = properties.getProperty("APP_KEY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{APP_KEY, APP_ID};
    }

    public String getBASE_URL() {
        String APP_KEY = "";
        String APP_ID = "";
        try {
            String[] keys = this.getKeysFile();
            APP_KEY = keys[0];
            APP_ID = keys[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "https://transportapi.com/v3/uk/places.json?app_id=" + APP_ID + "&app_key=" + APP_KEY;
    }
}
