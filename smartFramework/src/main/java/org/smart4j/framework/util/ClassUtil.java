package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public final class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        // 获取当前线程的类加载器
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     *
     * @param className     需要加载的类名
     * @param isInitialized 是否执行类的静态代码块
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
            logger.info("load success " + cls.getName());
        } catch (ClassNotFoundException e) {
            logger.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定包名下(包括子包)的所有类
     *
     * @param packageName 需要被添加的包名
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            // 获取该包下的所有资源
            Enumeration<URL> urlEnumeration = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                // 获取此URL的协议
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    String packagePath = url.getPath().replaceAll("%20", "");
                    addClass(classSet, packagePath, packageName);
                }
            }
        } catch (IOException e) {
            logger.error("get class set failure", e);
        }
        return classSet;
    }

    /**
     * 装载指定包路径下的所有.class文件
     *
     * @param classSet    存放成功装载的类集合
     * @param packagePath 包的真实绝对路径
     * @param packageName 包名
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        // 获取指定包名下的以.class结尾的文件和子包
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });
        if (files == null) {
            return;
        }
        for (File file : files) {
            String fileName = file.getName();
            // 如果为.class文件,获取全限定名,然后装载它
            if (file.isFile()) {
                // 去掉.class后缀
                String className = fileName.substring(0, fileName.indexOf("."));
                // 拼接全限定名(包名+类名)
                className = packageName + "." + className;
                // 转载指定类,并加入set集合中
                doLoadClass(classSet, className);
            } else {    // 如果为子包
                // 拼接获得子包绝对路径(父包绝对路径 + 子包名)
                String subPackageName = file.getName();
                String subPackagePath = packagePath + "/" + subPackageName;
                // 子包的全限定名
                subPackageName = packageName + "." + subPackageName;
                addClass(classSet, subPackagePath, subPackageName);
            }

        }
    }

    private static void doLoadClass(Set<Class<?>> classSet, String className) {
        // 装载class,装载成功返回该class
        Class<?> loadedClass = loadClass(className, false);
        // 装载成功的class加入set集合
        classSet.add(loadedClass);
    }
}
