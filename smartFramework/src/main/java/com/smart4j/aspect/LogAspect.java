package com.smart4j.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

@Aspect(Controller.class)
public class LogAspect extends AspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Override
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) {
        logger.info("target method:{}", targetMethod.getName());
    }

    @Override
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        logger.info("target method:{}", targetMethod.getName());
    }
}
