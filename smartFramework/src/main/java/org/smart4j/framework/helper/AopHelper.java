package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

public final class AopHelper {
    private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            // 获取切面类和目标类集合映射
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            // 获取目标类和代理类集合映射
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                // 通过目标类和代理类集合获取代理类
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                // 注入容器中
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            logger.error("aop failure", e);
        }
    }

    /**
     * 通过Aspect注解的Value()方法去获取目标对象集合
     *
     * @param aspect
     * @return
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        // 获取目标注解
        Class<? extends Annotation> targetAnnotation = aspect.value();
        // 通过注解找到类
        ClassHelper.getClassSetByAnnotation(targetAnnotation);
        // 注解不为Aspect注解,则去找出被该注解修饰的那些类
        if (!targetAnnotation.equals(Aspect.class)) {
            classSet.addAll(ClassHelper.getClassSetByAnnotation(targetAnnotation));
        }
        return classSet;
    }

    /**
     * 获取代理类和目标类集合之间的映射(一个代理类对应多个目标类)
     * (切面,目标类集合)
     *
     * @return
     */
    public static Map<Class<?>, Set<Class<?>>> createProxyMap() {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        // 获取切面类集合
        Set<Class<?>> aspectClassSet = ClassHelper.getClassSetBySupper(AspectProxy.class);
        // 遍历切面类集合,给切面和切面的目标类集合进行映射
        for (Class<?> aspectClass : aspectClassSet) {
            if (aspectClass.isAnnotationPresent(Aspect.class)) {
                // 提取修饰切面的Aspect注解
                Aspect aspectAnnotation = aspectClass.getAnnotation(Aspect.class);
                // 通过Aspect注解获取目标类集合
                Set<Class<?>> targetClassSet = createTargetClassSet(aspectAnnotation);
                // 添加映射
                proxyMap.put(aspectClass, targetClassSet);
            }
        }
        return proxyMap;
    }

    /**
     * 获取目标类和代理类集合之间的映射
     * (目标类,代理类集合)
     *
     * @return
     */
    public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap)
            throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        // 遍历(切面,目标类集合)
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> aspectClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            // 遍历目标类集合
            for (Class<?> targetClass : targetClassSet) {
                // 实例化切面对象
                Proxy aspectInstance = (Proxy) aspectClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(aspectInstance);
                } else {
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(aspectInstance);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }
}
