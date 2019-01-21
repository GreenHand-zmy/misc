package com.smart4j.controller;

import com.smart4j.domain.User;
import com.smart4j.service.UserService;
import com.smart4j.service.impl.UserServiceImpl;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

@Controller
public class HelloController {
    @Inject
    private UserServiceImpl UserService;

    @Action("get:/user")
    public View say(Param param) {
        String username = param.getString("username");
        User user = new User(username);
        UserService.sayHelloTo(user);
        return new View("index.jsp").addModel("user", user);
    }

    @Action("get:/hello")
    public Data user() {
        User user = new User("Peter");
        Data data = new Data(user);
        return data;
    }
}
