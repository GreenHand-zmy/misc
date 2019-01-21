package com.hongbao.demo.service.impl;

import com.hongbao.demo.domain.CookieInfo;
import com.hongbao.demo.domain.RedPacketURLInfo;
import com.hongbao.demo.domain.request.ChangPhoneRequest;
import com.hongbao.demo.domain.request.WeiXinRequest;
import com.hongbao.demo.service.RequestService;
import com.hongbao.demo.utils.JsonUtils;
import org.springframework.stereotype.Service;

/**
 * Created by zmy on 2018/5/4.
 */
@Service
public class RequestServiceImpl implements RequestService {

    // 抢红包服务器restApi
    private static final String RED_PACKET_RESTFUL_API_POST = "https://h5.ele.me/restapi/marketing/promotion/weixin/{openId}";

    // 修改手机号服务器restApi
    private static final String CHANGE_PHONE_RESTFUL_API_PUT = "https://h5.ele.me/restapi/v1/weixin/{openId}/phone";

    @Override
    public String getGrabRedPacketRequest(CookieInfo cookieInfo, RedPacketURLInfo redPacketURLInfo, String phone) {
        WeiXinRequest request = new WeiXinRequest();

        // 设置必须值
        request.setSign(cookieInfo.getElemeKey());
        request.setWeixinAvatar(cookieInfo.getAvatar());
        request.setWeixinUsername(cookieInfo.getNickName());
        request.setGroupSn(redPacketURLInfo.getSn());
        request.setPlatform(redPacketURLInfo.getPlatform());
        request.setPhone(phone);

        // 返回结果请求
        return JsonUtils.toString(request);
    }

    @Override
    public String getChangeJsonPhoneRequest(CookieInfo cookieInfo, String phone) {
        String sign = cookieInfo.getElemeKey();
        ChangPhoneRequest request = new ChangPhoneRequest();
        request.setSign(sign);
        request.setPhone(phone);
        return JsonUtils.toString(request);
    }

    @Override
    public String getRestfulApiForRedPacket(CookieInfo cookieInfo) {
        return getRestApi(RED_PACKET_RESTFUL_API_POST, cookieInfo.getOpenId());
    }

    @Override
    public String getRestfulApiForChangePhone(CookieInfo cookieInfo) {
        return getRestApi(CHANGE_PHONE_RESTFUL_API_PUT, cookieInfo.getOpenId());
    }

    /**
     * 按照openid格式化
     *
     * @param openId openId
     * @return
     */
    private String getRestApi(String restfulApi, String openId) {
        return restfulApi.replace("{openId}", openId);
    }
}
