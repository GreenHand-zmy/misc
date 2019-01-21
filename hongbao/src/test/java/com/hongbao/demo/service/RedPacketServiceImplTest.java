package com.hongbao.demo.service;

import com.hongbao.demo.domain.JSONResult;
import com.hongbao.demo.domain.RedPacketURLInfo;
import com.hongbao.demo.entity.Cookie;
import com.hongbao.demo.service.impl.CookieServiceImpl;
import com.hongbao.demo.service.impl.RedPacketServiceImpl;
import com.hongbao.demo.service.impl.RedPacketURLServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedPacketServiceImplTest {
    @Autowired
    private RedPacketServiceImpl redPacketServiceImpl;
    @Autowired
    private CookieService cookieService;

    @Test
    public void doGrabRedEnvelope() {
        redPacketServiceImpl.doGrabRedEnvelope("18100177470", "https://h5.ele.me/hongbao/?from=singlemessage&isappinstalled=0#hardware_id=&is_lucky_group=True&lucky_number=7&track_id=&platform=0&sn=29f2da30beaea0bb&theme_id=2449&device_id=&refer_user_id=52474786");
    }

    @Test
    public void doOnceGrapTest() {
        Cookie cookie = cookieService.findById(59L);
        RedPacketURLService redPacketURLService = new RedPacketURLServiceImpl();
        // 得到红包链接详情
        RedPacketURLInfo redPacketURLInfo = redPacketURLService.getRedPacketURLInfo("https://h5.ele.me/hongbao/?from=singlemessage&isappinstalled=0#hardware_id=&is_lucky_group=True&lucky_number=10&track_id=&platform=0&sn=29f474d21fae4c83&theme_id=2505&device_id=&refer_user_id=39471887");
        JSONResult jsonResult = redPacketServiceImpl.doOnceGrabRedEnvelope("18100177472", redPacketURLInfo, cookie);
    }
}