package com.peter.openx.core.impl;

import com.peter.openx.core.ClassScanner;
import com.peter.openx.core.impl.support.AnnotationClassTemplate;
import com.peter.openx.core.impl.support.ClassTemplate;
import com.peter.openx.core.impl.support.SupperClassTemplate;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by zmy on 2018/5/17.
 */
public class DefaultClassScanner implements ClassScanner {
    @Override
    public Set<Class<?>> getClassSet(String packageName) {
        return new ClassTemplate(packageName) {
            @Override
            public boolean addClassCondition(Class<?> cls) {
                return true;
            }
        }.getClassSet();
    }

    @Override
    public Set<Class<?>> getClassSetByAnnotation(String packageName,
                                                 Class<? extends Annotation> annotationClass) {
        return new AnnotationClassTemplate(packageName, annotationClass) {
            @Override
            public boolean addClassCondition(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassSet();
    }

    @Override
    public Set<Class<?>> getClassSetBySuper(String packageName, Class<?> superClass) {
        return new SupperClassTemplate(packageName, superClass) {
            @Override
            public boolean addClassCondition(Class<?> cls) {
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls);
            }
        }.getClassSet();
    }
}
