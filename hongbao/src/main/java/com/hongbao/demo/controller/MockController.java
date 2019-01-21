package com.hongbao.demo.controller;

import com.hongbao.demo.entity.Cookie;
import com.hongbao.demo.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MockController {
    @Autowired
    private CookieService cookieService;

    @RequestMapping("/get")
    public String mock(String redPacketUrl) {
        return "hello";
    }

    @RequestMapping("/Cookie/{cookieId}")
    public Cookie getCookie(@PathVariable("cookieId") Long cookieId) {
        ModelMap modelMap = new ModelMap();
        return cookieService.findById(cookieId);
    }
}
