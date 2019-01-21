package org.smart4j.framework.helper;

import org.junit.Test;
import org.smart4j.framework.util.ClassUtil;

public class IocHelpTest {
    @Test
    public void testIoc() {
        ClassUtil.loadClass(IocHelper.class.getName(), true);

    }
}