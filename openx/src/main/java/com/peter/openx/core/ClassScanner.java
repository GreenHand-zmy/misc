package com.peter.openx.core;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 加载类
 */
public interface ClassScanner {

    /**
     * 获取指定包名下的所有类
     *
     * @param packageName 包名
     * @return
     */
    Set<Class<?>> getClassSet(String packageName);

    /**
     * 获取指定包名下带有指定注解的类集合
     *
     * @param packageName
     * @param annotationClass
     * @return
     */
    Set<Class<?>> getClassSetByAnnotation(String packageName,
                                          Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的类集合
     *
     * @param packageName
     * @param superClass
     * @return
     */
    Set<Class<?>> getClassSetBySuper(String packageName, Class<?> superClass);
}
