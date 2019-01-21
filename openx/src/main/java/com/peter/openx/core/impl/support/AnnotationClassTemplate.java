package com.peter.openx.core.impl.support;

import java.lang.annotation.Annotation;
import java.util.Set;

public abstract class AnnotationClassTemplate extends ClassTemplate {
    protected Class<? extends Annotation> annotationClass;

    public AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}
