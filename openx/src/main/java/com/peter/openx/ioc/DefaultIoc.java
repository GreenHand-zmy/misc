package com.peter.openx.ioc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultIoc implements Ioc {
    // 存放<bean名,bean实例>映射的map
    private Map<String, Object> beanMap = new ConcurrentHashMap<>(32);

    @Override
    public void addBean(Object bean) {
        // 默认以bean实例的class为bean名
        String beanName = bean.getClass().getName();

        beanMap.put(beanName, bean);
    }

    @Override
    public void addBean(String beanName, Object bean) {

    }

    @Override
    public <T> T addBean(Class<T> type) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return null;
    }

    @Override
    public List<Object> getBeans() {
        return null;
    }

    @Override
    public Set<String> getBeanNames() {
        return null;
    }

    @Override
    public void remove(Class<?> type) {

    }

    @Override
    public void remove(String beanName) {

    }
}
