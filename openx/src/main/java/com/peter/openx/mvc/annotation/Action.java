package com.peter.openx.mvc.annotation;

import com.peter.openx.mvc.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     * 请求路径
     *
     * @return
     */
    String value() default "";

    /**
     * http请求方式
     *
     * @return
     */
    RequestMethod method() default RequestMethod.GET;
}
