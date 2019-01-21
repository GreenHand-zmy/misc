package com.hongbao.demo.service.impl;

import com.hongbao.demo.domain.RedPacketURLInfo;
import com.hongbao.demo.service.RedPacketURLService;
import com.hongbao.demo.utils.HttpUtils;
import org.springframework.stereotype.Service;

@Service
public class RedPacketURLServiceImpl implements RedPacketURLService {
    // 第几个为大红包
    private static final String LUCK_NUMBER = "lucky_number";
    // 未知参数
    private static final String SN = "sn";
    // 来自谁的红包链接
    private static final String REFER_USER_ID = "refer_user_id";
    // 平台编号
    private static final String PLATFORM = "platform";

    @Override
    public RedPacketURLInfo getRedPacketURLInfo(String redPacketURL) {
        // 构造红包url实例
        RedPacketURLInfo redPacketURLInfo = new RedPacketURLInfo();
        redPacketURLInfo.setLuckNumber(Integer.parseInt(HttpUtils.getParamFromUrl(redPacketURL, LUCK_NUMBER)));
        redPacketURLInfo.setSn(HttpUtils.getParamFromUrl(redPacketURL, SN));
        redPacketURLInfo.setReferUserId(HttpUtils.getParamFromUrl(redPacketURL, REFER_USER_ID));
        redPacketURLInfo.setPlatform(Integer.parseInt(HttpUtils.getParamFromUrl(redPacketURL, PLATFORM)));
        return redPacketURLInfo;
    }
}
