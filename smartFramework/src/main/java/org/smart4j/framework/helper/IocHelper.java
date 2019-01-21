package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 */
public final class IocHelper {
    static {
        // 1.获取Bean类与Bean实例的映射
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        // 2.遍历映射获取beanClass和beanInstance
        if (!beanMap.isEmpty()) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 3.获取Bean类中类成员带有@Inject注解的类成员
                Field[] fields = beanClass.getDeclaredFields();
                if (fields.length > 0) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            // 在beanMap中查找对应的实例
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            // 实例不为空,则注入
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
