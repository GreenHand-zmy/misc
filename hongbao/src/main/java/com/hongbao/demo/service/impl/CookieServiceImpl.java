package com.hongbao.demo.service.impl;

import com.hongbao.demo.domain.CookieInfo;
import com.hongbao.demo.entity.Cookie;
import com.hongbao.demo.entity.User;
import com.hongbao.demo.mapper.CookieMapper;
import com.hongbao.demo.service.CookieService;
import com.hongbao.demo.utils.JsonUtils;
import com.hongbao.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import static com.hongbao.demo.utils.CheckUtils.check;

/**
 * Created by zmy on 2018/5/4.
 */
@Service
public class CookieServiceImpl implements CookieService {
    // 期望存在的cookie
    public static final String EXPECT_COOKIE_KEY = "snsInfo[101204453]";
    // 最少拿取cookie的数量
    private static final int MIN_FETCH_SIZE = 10;
    @Autowired
    private CookieMapper cookieMapper;

    @Override
    @Transactional
    public int saveCookie(User user, String cookies) {
        // 序列化期望出现的cookie,并返回详情信息
        CookieInfo cookieInfo = getCookieInfo(cookies, EXPECT_COOKIE_KEY);

        // 判断必须字段是否都存在
        check(isCookieInfoValid(cookieInfo), "您提交的cookie部分缺失,请检查再提交");

        // 判断当前用户是否为空
        check(user != null && user.getUserId() != null, "无效的cookie提交请求");

        // 判断cookie是否已存在
        check(cookieMapper.countByElemeKey(cookieInfo.getElemeKey()) == 0,
                "该cookie已经存在,请重新换一个提交");

        // 构造cookie对象,持久化到数据库
        Cookie cookie = new Cookie();
        cookie.setCookieInfo(cookieInfo);
        cookie.setCookieContent(cookies);
        cookie.setUser(user);

        // 保存到数据库
        return cookieMapper.save(cookie);
    }

    @Override
    public List<Cookie> getAvailableCookie(int length) {
        List<Cookie> cookieInfoList = cookieMapper.findAvailableCookie(length);

        //  cookie数不足抛出异常
        if (cookieInfoList.size() < MIN_FETCH_SIZE) {
            throw new RuntimeException("数据库cookie不足");
        }
        return cookieInfoList;
    }

    @Override
    @Transactional
    public int batchUpdate(List<Cookie> cookieList) {
        int updateSize = 0;
        for (Cookie cookie : cookieList) {
            updateSize += cookieMapper.update(cookie);
        }
        return updateSize;
    }

    @Override
//    @Cacheable(value = "cookie", key = "#id",unless = "#result eq null")
    public Cookie findById(Long id) {
        return cookieMapper.findById(id);
    }

    /**
     * 将期望cookie的值序列化
     *
     * @param expectCookieName 期望的cookie
     * @return
     */
    private CookieInfo getCookieInfo(String expectCookieName) {
        try {
            // 首先进行url转码
            expectCookieName = URLDecoder.decode(expectCookieName, "utf-8");

            // 获取cookie中的value
            String[] cookieKeyValue = expectCookieName.split("=");

            check(cookieKeyValue.length == 2, "此cookie 没有 value");

            String value = cookieKeyValue[1];

            // value为json进行转换
            return JsonUtils.toObject(value, CookieInfo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据key获取cookie值并序列化
     *
     * @param cookies
     * @param key
     * @return
     */
    private CookieInfo getCookieInfo(String cookies, String key) {
        // 数据校验
        check(!StringUtils.isEmpty(cookies), "cookies 不能为空");
        check(!StringUtils.isEmpty(key), "cookie key 不能为空");

        // 根据';',分割cookies
        String[] cookiesArray = cookies.split(";");
        check(cookiesArray.length > 0, "不是有效的cookie");

        // 遍历cookie数组,判断是否存在我们需要的cookie
        for (String currentCookie : cookiesArray) {

            // 找到我们需要的cookie
            if (currentCookie.contains(key)) {
                // 序列化cookie
                return getCookieInfo(currentCookie);
            }
        }

        // 没有找到需要cookie抛出异常
        throw new RuntimeException("输入的cookie没有" + EXPECT_COOKIE_KEY);
    }

    /**
     * 判断该cookie信息是否有效
     *
     * @param cookieInfo 待验证的cookie信息
     * @return 验证通过返回true
     */
    private boolean isCookieInfoValid(CookieInfo cookieInfo) {
        if (StringUtils.isEmpty(cookieInfo.getElemeKey())) {
            return false;
        }
        if (StringUtils.isEmpty(cookieInfo.getOpenId())) {
            return false;
        }
        return true;
    }
}
