package com.hongbao.demo.service;

import com.hongbao.demo.domain.RedPacketURLInfo;

public interface RedPacketURLService {
    /**
     * 获取红包链接详情信息
     *
     * @param redPacketURL 红包链接
     * @return
     */
    RedPacketURLInfo getRedPacketURLInfo(String redPacketURL);
}
