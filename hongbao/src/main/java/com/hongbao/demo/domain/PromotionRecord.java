package com.hongbao.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 红包列表中一项红包记录
 */
@Data
public class PromotionRecord implements Serializable {

    // 红包结果列表中显示的名字
    @JsonProperty("sns_username")
    private String username;

    // 抢到的红包金额
    @JsonProperty("amount")
    private BigDecimal amount;

    // 是否为最大红包
    @JsonProperty("is_lucky")
    private Boolean isLucky;

    // 红包列表显示头像
    @JsonProperty("sns_avatar")
    private String avatar;

    // 抢红包的时刻
    @JsonProperty("created_at")
    private Date createTime;
}
