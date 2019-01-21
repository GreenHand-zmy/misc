package com.smart4j.proxy;

import com.smart4j.domain.User;
import com.smart4j.service.UserService;
import com.smart4j.service.impl.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    @Test
    public void testJDKProxy() {
        UserService userService = new UserServiceImpl();
        Object proxyInstance = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{UserService.class},
                new DynamicProxy(userService));
        UserService proxy = (UserService) proxyInstance;
        User user = new User("peter");
        proxy.sayHelloTo(user);
    }

    @Test
    public void testCglibProxy() {

    }
}