package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     *
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            result = method.invoke(object, args);
        } catch (Exception e) {
            logger.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     *
     * @param object
     * @param field
     * @param value
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            logger.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 筛选出符合指定注解的类成员集合
     *
     * @param fields
     * @param annotation
     * @return
     */
    public static List<Field> filterField(Field[] fields, Class<? extends Annotation> annotation) {
        List<Field> fieldList = new ArrayList<Field>();
        if (annotation == null) {
            return null;
        }
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotation)) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }
}
