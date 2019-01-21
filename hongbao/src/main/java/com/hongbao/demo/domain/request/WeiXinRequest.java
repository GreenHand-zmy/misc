package com.hongbao.demo.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 传送到饿了么抢红包服务器的json对象
 */
@Data
public class WeiXinRequest implements Serializable {
    // 抢红包方式
    @JsonProperty("method")
    private String method = "phone";

    // 不知何意
    @JsonProperty("group_sn")
    private String groupSn;

    // 签名
    @JsonProperty("sign")
    private String sign;

    // 抢红包的手机号
    @JsonProperty("phone")
    private String phone;

    // 平台编号
    @JsonProperty("platform")
    private Integer platform;

    // 显示的头像地址
    @JsonProperty("weixin_avatar")
    private String weixinAvatar;

    // 实现的用户名
    @JsonProperty("weixin_username")
    private String weixinUsername;

    // 不知何意
    @JsonProperty("track_id")
    private String trackId = "undefined";

    // 不知何意
    @JsonProperty("unionid")
    private String unionid = "fuck";

    public WeiXinRequest() {
    }
}
