package com.peter.openx.core.impl;

import com.peter.openx.FrameworkConstant;
import com.peter.openx.core.PropertiesReader;
import com.peter.openx.util.ClassUtil;
import com.peter.openx.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by zmy on 2018/5/17.
 */
public class DefaultPropertiesReader implements PropertiesReader {

    private Properties properties;

    public DefaultPropertiesReader() {
        doLoad();
    }

    @Override
    public Optional<String> getPropertyAsString(String key) {
        return Optional.ofNullable(properties.getProperty(key));
    }

    @Override
    public Optional<Integer> getPropertyAsInteger(String key) {
        String property = properties.getProperty(key);
        if (StringUtil.isEmpty(property)) {
            return Optional.empty();
        }
        return Optional.of(Integer.parseInt(property));
    }

    @Override
    public Optional<Long> getPropertyAsLong(String key) {
        String property = properties.getProperty(key);
        if (StringUtil.isEmpty(property)) {
            return Optional.empty();
        }
        return Optional.of(Long.parseLong(property));
    }

    @Override
    public Optional<Boolean> getPropertyAsBoolean(String key) {
        String property = properties.getProperty(key);
        if (StringUtil.isEmpty(property)) {
            return Optional.empty();
        }
        return Optional.of(Boolean.valueOf(property));
    }

    private void doLoad() {
        properties = new Properties();
        InputStream input = null;
        try {
            // 获取默认配置文件
            input = ClassUtil.getClassLoader()
                    .getResourceAsStream(FrameworkConstant.DEFAULT_CONFIG_PROPERTIES);

            // 执行读取配置文件
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
