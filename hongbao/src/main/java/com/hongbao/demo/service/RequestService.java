package com.hongbao.demo.service;

import com.hongbao.demo.domain.CookieInfo;
import com.hongbao.demo.domain.RedPacketURLInfo;

public interface RequestService {
    /**
     * 将cookInfo信息,红包链接信息,用户手机号组合为抢红包的请求
     *
     * @param cookieInfo       cookie详情信息
     * @param redPacketURLInfo 红包地址
     * @param phone            用户手机号
     * @return 请求的json类型
     */
    String getGrabRedPacketRequest(CookieInfo cookieInfo, RedPacketURLInfo redPacketURLInfo, String phone);

    /**
     * 获取修改手机号的请求
     *
     * @param cookieInfo cookie详情信息
     * @param phone      目标手机号
     * @return 请求的json类型
     */
    String getChangeJsonPhoneRequest(CookieInfo cookieInfo, String phone);

    /**
     * 获取抢红包服务器的api
     *
     * @param cookieInfo 用户cookie详情信息
     * @return
     */
    String getRestfulApiForRedPacket(CookieInfo cookieInfo);

    /**
     * 获取修改手机号服务器的api
     *
     * @param cookieInfo 用户cookie详情信息
     * @return
     */
    String getRestfulApiForChangePhone(CookieInfo cookieInfo);
}
