package com.hongbao.demo.mapper;

import com.hongbao.demo.entity.Cookie;

import java.util.List;

/**
 * Created by zmy on 2018/5/4.
 */
public interface CookieMapper {
    /**
     * 根据cookie编号查询某个cookie
     *
     * @param cookieId cookie编号
     * @return 返回cookie信息
     */
    Cookie findById(Long cookieId);

    /**
     * 根据饿了么编号查询数据库是否已经存在该cookie
     *
     * @param elemeKey
     * @return
     */
    Integer countByElemeKey(String elemeKey);

    /**
     * 找出所有cookie
     *
     * @return 返回所以的cookie信息
     */
    List<Cookie> findAll();

    /**
     * 找出可用的cookie
     *
     * @param number 限制多少条
     * @return 返回number条数据
     */
    List<Cookie> findAvailableCookie(Integer number);

    /**
     * 根据cookie编号更新cookie信息
     *
     * @param cookie 需要更改的cookie实例
     * @return sql影响行数
     */
    int update(Cookie cookie);

    /**
     * 保存一条cookie
     *
     * @param cookie cookie对象
     * @return sql影响行数
     */
    int save(Cookie cookie);
}
