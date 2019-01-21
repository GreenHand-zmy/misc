package com.peter.openx.ioc;

import java.util.List;
import java.util.Set;

/**
 * IOC 容器,它提供了注册bean、获取bean、删除bean的一系列功能
 */
public interface Ioc {
    /**
     * 向IOC容器中添加一个bean实例
     *
     * @param bean bean实例
     */
    void addBean(Object bean);

    /**
     * 向IOC容器中添加一个bean实例
     *
     * @param beanName bean名
     * @param bean     bean实例
     */
    void addBean(String beanName, Object bean);

    /**
     * 通过class创建bean实例,并且注册到IOC容器中
     *
     * @param type bean class type
     * @param <T>  class type
     * @return 返回无参构造函数的bean实例
     */
    <T> T addBean(Class<T> type);

    /**
     * 通过bean名,获取bean实例
     *
     * @param beanName bean名
     * @return bean实例
     */
    Object getBean(String beanName);

    /**
     * 通过class type获取bean实例
     *
     * @param type bean class type
     * @param <T>  class type
     * @return 返回bean实例
     */
    <T> T getBean(Class<T> type);

    /**
     * 获取容器中所有的bean实例
     *
     * @return 返回bean实例list
     */
    List<Object> getBeans();

    /**
     * 返回所有bean名
     *
     * @return 返回bean名 set
     */
    Set<String> getBeanNames();

    /**
     * 通过class type删除容器中的bean实例
     *
     * @param type bean class type
     */
    void remove(Class<?> type);

    /**
     * 通过bean名删除容器中bean实例
     *
     * @param beanName bean名
     */
    void remove(String beanName);
}
