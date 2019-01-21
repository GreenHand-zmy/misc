package com.hongbao.demo.service;

public interface RedPacketService {
    /**
     * 输入手机号和红包路径,执行抢红包
     *
     * @param phone        领取红包的手机号
     * @param redPacketURL 红包路径
     * @return
     */
    void doGrabRedEnvelope(String phone, String redPacketURL);
}
