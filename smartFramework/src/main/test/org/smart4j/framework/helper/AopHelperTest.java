package org.smart4j.framework.helper;

import org.junit.BeforeClass;
import org.junit.Test;
import org.smart4j.framework.HelperLoader;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class AopHelperTest {
    @BeforeClass
    public static void init() {
        // 初始化容器
        HelperLoader.init();
    }

    @Test
    public void createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = AopHelper.createProxyMap();
    }

}