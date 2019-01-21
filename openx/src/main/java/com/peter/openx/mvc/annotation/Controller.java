package com.peter.openx.mvc.annotation;

import com.peter.openx.ioc.annotation.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Controller {
    /**
     * 控制器名
     *
     * @return
     */
    String value() default "";
}
