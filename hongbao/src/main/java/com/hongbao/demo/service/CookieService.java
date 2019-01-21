package com.hongbao.demo.service;

import com.hongbao.demo.entity.Cookie;
import com.hongbao.demo.entity.User;

import java.util.List;

public interface CookieService {
    /**
     * 保存cookie
     *
     * @param user    用户
     * @param cookies 用户传进来的cookie数组
     * @return
     */
    int saveCookie(User user, String cookies);

    /**
     * 获取数据库中还有次数的cookie
     *
     * @param length 需要的个数
     * @return
     */
    List<Cookie> getAvailableCookie(int length);

    /**
     * 批量更新cookie信息
     *
     * @param cookieList
     * @return
     */
    int batchUpdate(List<Cookie> cookieList);

    /**
     * 根据cookie编号查询cookie
     *
     * @param id
     * @return
     */
    Cookie findById(Long id);
}
