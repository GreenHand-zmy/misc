package com.hongbao.demo.mapper;

import com.hongbao.demo.entity.Cookie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zmy on 2018/5/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CookieMapperTest {
    @Autowired
    private CookieMapper cookieMapper;

    @Test
    public void findById() throws Exception {
        Cookie cookie = cookieMapper.findById(1L);
        System.out.println(cookie);
    }

    @Test
    public void countByElemeKey() {
        Integer integer = cookieMapper.countByElemeKey("5197aa6c281f7249a05a82b3ef41c89b");
    }

    @Test
    public void findAll() throws Exception {
        List<Cookie> cookieList = cookieMapper.findAll();
    }

    @Test
    public void findAvailableCookie() {
        List<Cookie> availableCookie = cookieMapper.findAvailableCookie(5);
    }

    @Test
    public void update() {
        Cookie cookie = cookieMapper.findById(1L);
        cookie.setModified(2);
        cookieMapper.update(cookie);
    }
}