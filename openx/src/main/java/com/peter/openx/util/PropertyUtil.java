package com.peter.openx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static Properties loadProperties(String propertiesPath) {
        CheckUtil.check(StringUtil.isNotEmpty(propertiesPath), "加载属性文件出错");

        Properties properties = new Properties();

        InputStream input = null;
        try {
            input = ClassUtil.getClassLoader().getResourceAsStream(propertiesPath);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
