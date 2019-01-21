package com.hongbao.demo.entity;

import com.hongbao.demo.domain.CookieInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库对应的cookie表
 * Created by zmy on 2018/5/4.
 */
@Data
public class Cookie implements Serializable {
    // cookie编号
    private Long cookieId;

    // cookie内容
    private String cookieContent;

    // cookie详情信息
    private CookieInfo cookieInfo;

    // 所属用户
    private User user;

    // cookie被操作次数
    private Integer modified;

    // 是否被删除
    private boolean del;

    // 生成时间
    private Date gmtCreate;

    // 修改时间
    private Date gmtModified;
}
