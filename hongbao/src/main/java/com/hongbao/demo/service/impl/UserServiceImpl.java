package com.hongbao.demo.service.impl;

import com.hongbao.demo.entity.User;
import com.hongbao.demo.mapper.UserMapper;
import com.hongbao.demo.service.UserService;
import com.hongbao.demo.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Long userId) {
        // 向数据库查询用户
        User user = userMapper.findById(userId);

        // 判断用户是否存在,不存着抛出异常
        CheckUtils.check(user != null && user.getUserId() != null,
                "无此用户");

        return user;
    }
}
