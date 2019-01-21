package com.smart4j.service.impl;

import com.smart4j.domain.User;
import com.smart4j.service.UserService;
import org.smart4j.framework.annotation.Service;

@Service
public class UserServiceImpl implements UserService {

    public void sayHelloTo(User user) {
        System.out.println("hello " + user.getUsername());
    }
}
