package com.hongbao.demo.mapper;

import com.hongbao.demo.entity.User;

public interface UserMapper {
    /**
     * 根据用户编号查询
     *
     * @param userId 用户编号
     * @return 查询结果, 可能会空
     */
    User findById(Long userId);
}
