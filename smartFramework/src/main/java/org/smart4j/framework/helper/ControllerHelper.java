package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类(将Controller类中带有@Action注解的方法处理为请求和处理器映射)
 */
public final class ControllerHelper {
    // 存放请求和处理器器的映射
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        // 1.获取所有带有Controller注解的类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        // 2.遍历controllerClass集合
        for (Class<?> controllerClass : controllerClassSet) {
            // 3.找出controllerClass中带有@Action注解的方法
            Method[] controllerClassMethods = controllerClass.getMethods();
            if (controllerClassMethods.length > 0) {
                for (Method controllerClassMethod : controllerClassMethods) {
                    if (controllerClassMethod.isAnnotationPresent(Action.class)) {
                        // 4.从注解中获取URL映射规则
                        Action action = controllerClassMethod.getAnnotation(Action.class);
                        String mapping = action.value();
                        // 5.验证URL映射规则
                        if (mapping.matches("\\w+:/\\w*")) {
                            String[] split = mapping.split(":");
                            if (split.length > 0 && split.length == 2) {
                                // 获取请求方法和请求路径
                                String requestMethod = split[0];
                                String requestPath = split[1];
                                // 构造请求对象
                                Request request = new Request(requestMethod, requestPath);
                                // 构造处理器
                                Handler handler = new Handler(controllerClass, controllerClassMethod);
                                // 添加到集合中
                                ACTION_MAP.put(request, handler);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 通过请求方法和请求路径获取处理器
     *
     * @param requestMethod 请求方法(get post之类的Http方法)
     * @param requestPath   请求路径
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
