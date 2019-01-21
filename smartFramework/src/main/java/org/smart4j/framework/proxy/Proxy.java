package org.smart4j.framework.proxy;

/**
 * 代理接口
 */
public interface Proxy {
    /**
     * 执行链式代理
     *
     * @param proxyChain
     * @return
     */
    Object doProxy(ProxyChain proxyChain);
}
