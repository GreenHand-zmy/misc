package org.smart4j.framework.helper;

import org.junit.BeforeClass;
import org.junit.Test;
import org.smart4j.framework.HelperLoader;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;

import java.util.Set;

import static org.junit.Assert.*;

public class ClassHelperTest {
    @BeforeClass
    public static void init() {
        // 初始化容器
        HelperLoader.init();
    }

    @Test
    public void getClassSetBySupper() throws Exception {
        ClassHelper.getClassSetBySupper(AspectProxy.class);
    }

    @Test
    public void getClassSetByAnnotation() throws Exception {
        Set<Class<?>> classSet = ClassHelper.getClassSetByAnnotation(Aspect.class);
    }
}