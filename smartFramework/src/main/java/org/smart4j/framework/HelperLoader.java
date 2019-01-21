package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                // 装载配置文件指定的包名下的所有类,并放入到集合中
                ClassHelper.class,
                // 实例化所有类,并添加映射(相当于容器)
                BeanHelper.class,
                // 为容器提供Aop功能
                AopHelper.class,
                // 为带有@Inject注解的类成员注入实例
                IocHelper.class,
                // 将Controller类中带有@Action注解的方法,处理为请求和处理器的映射
                ControllerHelper.class
        };
        for (Class<?> helperClass : classList) {
            ClassUtil.loadClass(helperClass.getName(), true);
        }
    }
}
