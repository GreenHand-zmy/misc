package com.hongbao.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 抢红包结果(json格式)
 */
@Data
public class JSONResult implements Serializable {

    // 手机号
    @JsonProperty("account")
    private String account;

    //幸运的状态(具体未知)
    @JsonProperty("luck_status")
    private String luckStatus;

    // 是否为最大红包
    @JsonProperty("is_lucky")
    private Boolean isLucky;

    // 已抢列表
    @JsonProperty("promotion_records")
    List<PromotionRecord> promotionRecords;
}
