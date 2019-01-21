package org.smart4j.framework.helper;

import org.junit.Test;
import org.smart4j.framework.util.ClassUtil;
import org.smart4j.framework.util.ReflectionUtil;

import static org.junit.Assert.*;

public class ControllerHelperTest {
    @Test
    public void getHandler() throws Exception {
        ClassUtil.loadClass(ControllerHelper.class.getName(), true);
    }

}