package com.peter.openx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成所需组件工厂(用户可定制)
 */
public class InstanceFactory {
    // 存放组件缓存
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    /**
     * classScanner
     */
    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        // 若缓存中存在对应的实例,则返回实例
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }

        // 从配置文件中
        return null;
    }

}
