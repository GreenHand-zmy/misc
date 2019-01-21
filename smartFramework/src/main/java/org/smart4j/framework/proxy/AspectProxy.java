package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AspectProxy implements Proxy {
    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    public Object doProxy(ProxyChain proxyChain) {
        Object result = null;
        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] methodParams = proxyChain.getMethodParams();
        // 开始的钩子方法
        begin();
        try {
            // 是否进行拦截
            if (intercept(targetClass, targetMethod, methodParams)) {
                // 前置增强的钩子方法
                before(targetClass, targetMethod, methodParams);
                // 执行方法原本逻辑
                result = proxyChain.doProxyChain();
                // 后置增强的钩子方法
                after(targetClass, targetMethod, methodParams, result);
            } else {
                // 执行方法原本逻辑
                result = proxyChain.doProxyChain();
            }
        } catch (Throwable throwable) {
            logger.error("proxy failure", throwable);
            // 异常增强的钩子方法
            error(targetClass, targetMethod, methodParams, throwable);
        } finally {
            // 最后返回的增强的钩子方法
            end();
        }
        return result;
    }

    public void end() {
    }

    public void error(Class<?> targetClass, Method targetMethod, Object[] methodParams, Throwable throwable) {
    }

    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) {
    }

    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
    }

    /**
     * 返回是否进行拦截(默认为true)
     *
     * @param targetClass
     * @param targetMethod
     * @param methodParams
     * @return
     */
    public boolean intercept(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        return true;
    }

    /**
     * 开始的钩子方法
     */
    public void begin() {
    }

}
