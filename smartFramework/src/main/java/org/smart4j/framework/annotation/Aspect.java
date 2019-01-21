package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 对注解修饰的类拦截
     *
     * @return
     */
    Class<? extends Annotation> value();
}
