package com.peter.openx.core.impl.support;

import com.peter.openx.util.ClassUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zmy on 2018/5/17.
 */
public abstract class ClassTemplate {
    protected String packageName;

    public ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public final Set<Class<?>> getClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            // 获取指定包名下所有一级url
            Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));

            // 遍历所有一级url
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();

                String packagePath = url.getPath().replaceAll("%20", " ");

                // 加载指定包路径下的所有类
                addClass(classSet, packagePath, packageName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        // 获取指定包路径下的.class文件,或者目录
        File[] files = new File(packagePath).listFiles(pathname -> (pathname.isFile() && pathname.getName().endsWith(".class"))
                || pathname.isDirectory());

        // 递归退出条件
        if (files == null) {
            return;
        }

        // 遍历文件或目录
        Arrays.stream(files)
                .forEach(
                        file -> {
                            String fileName = file.getName();
                            // 如果为文件则添加到set中
                            if (file.isFile()) {
                                // 获取类名
                                String className = packageName + "." +
                                        fileName.substring(0, fileName.lastIndexOf("."));
                                // 添加到set中
                                doAddClass(classSet, className);
                            }
                            // 如果为目录递归获取子包直到获取到.class文件
                            else {
                                // 子包路径
                                String subPackagePath = packagePath + "/" + fileName;

                                // 子包名
                                String subPackageName = packageName + "." + fileName;

                                // 递归调用
                                addClass(classSet, subPackagePath, subPackageName);
                            }
                        }
                );
    }

    private void doAddClass(Set<Class<?>> classSet, String className) {
        // 加载类
        Class<?> cls = ClassUtil.loadClass(className, false);
        // 是否允许通过加载
        if (addClassCondition(cls)) {
            classSet.add(cls);
        }
    }

    public abstract boolean addClassCondition(Class<?> cls);
}
