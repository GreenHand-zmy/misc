package com.hongbao.demo.service;

import com.hongbao.demo.entity.User;

public interface UserService {
    /**
     * 根据用户编号查询
     *
     * @param userId 用户编号
     * @return 查询结果, 会空抛出异常
     */
    User findById(Long userId);
}
