package com.hongbao.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 从cookie中获取的信息
 */
@Data
public class CookieInfo implements Serializable {

    // 用户昵称
    @JsonProperty("nickname")
    private String nickName;

    // 用户性别
    @JsonProperty("gender")
    private String gender;

    // 用户生日年份
    @JsonProperty("year")
    private String year;

    // 用户省份
    @JsonProperty("province")
    private String province;

    // 用户城市
    @JsonProperty("city")
    private String city;

    // 饿了么专属编号
    @JsonProperty("eleme_key")
    private String elemeKey;

    // QQ授权openId
    @JsonProperty("openid")
    private String openId;

    // 头像url
    @JsonProperty("avatar")
    private String avatar;
}
