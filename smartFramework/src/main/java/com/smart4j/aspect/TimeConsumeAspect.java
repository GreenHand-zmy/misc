package com.smart4j.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

@Aspect(Controller.class)
public class TimeConsumeAspect extends AspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(TimeConsumeAspect.class);

    private long start;

    @Override
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        logger.debug("-------begin-------");
        logger.debug("class:{}", targetClass.getName());
        logger.debug("method:{}", targetMethod.getName());
        start = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) {
        logger.debug("time:{}", System.currentTimeMillis() - start);
        logger.debug("-------end-------");
    }
}
