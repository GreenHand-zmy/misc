package com.peter.openx.core.impl.support;

public abstract class SupperClassTemplate extends ClassTemplate {
    protected Class<?> superClass;

    public SupperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}
