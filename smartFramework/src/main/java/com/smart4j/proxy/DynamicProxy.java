package com.smart4j.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    // 目标类
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public void before() {
        System.out.println("before.....");
    }

    public void after() {
        System.out.println("after.....");
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }
}
