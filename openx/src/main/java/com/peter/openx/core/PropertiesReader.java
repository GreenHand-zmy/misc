package com.peter.openx.core;

import java.util.Optional;

/**
 * 从配置文件读取配置信息
 */
public interface PropertiesReader {
    /**
     * 返回String类型的键值
     *
     * @param key
     * @return
     */
    Optional<String> getPropertyAsString(String key);

    /**
     * 返回Integer类型的键值
     *
     * @param key
     * @return
     */
    Optional<Integer> getPropertyAsInteger(String key);

    /**
     * 返回Long类型的键值
     *
     * @param key
     * @return
     */
    Optional<Long> getPropertyAsLong(String key);

    /**
     * 返回Boolean类型的键值
     *
     * @param key
     * @return
     */
    Optional<Boolean> getPropertyAsBoolean(String key);
}
