package com.smart4j.proxy;

import com.smart4j.domain.User;
import com.smart4j.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class CGLIBProxyTest {

    @Test
    public void testCGLIBProxy() {
        CGLIBProxy cglibProxy = new CGLIBProxy();
        UserServiceImpl userService = cglibProxy.getProxy(UserServiceImpl.class);
        User user = new User("peter");
        userService.sayHelloTo(user);
    }
}