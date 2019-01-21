package com.hongbao.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 红包链接详情信息
 */
@Data
public class RedPacketURLInfo implements Serializable {

    // 最大红包数量
    private Integer luckNumber;

    // 未知参数
    private String sn;

    // 红包拥有者编号
    private String referUserId;

    // 平台编号
    private Integer platform;
}
