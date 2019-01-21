package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     *
     * @param filename
     * @return
     */
    public static Properties loadProps(String filename) {
        Properties properties = null;
        InputStream in = null;
        try {
            // 获取当前线程下的指定文件IO流
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if (in == null) {
                throw new FileNotFoundException(filename + " file is not found");
            }
            // 创建配置类
            properties = new Properties();
            // 从文件中载入配置
            properties.load(in);
        } catch (IOException e) {
            logger.error("load properties file '" + filename + "'failure", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("close input stream failure", e);
                }
            }
        }
        return properties;
    }

    /**
     * 获取字符型属性,可设置默认值
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    /**
     * 获取字符型属性,默认值为空
     *
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 获取int类型属性,可设置默认值
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;
        if (properties.containsKey(key)) {
            value = CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }

    /**
     * 获取int类型属性,默认值为0
     *
     * @param properties
     * @param key
     * @return
     */
    public static int getInt(Properties properties, String key) {
        return PropsUtil.getInt(properties, key, 0);
    }
}
