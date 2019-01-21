package com.hongbao.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应数据库用户表
 * Created by zmy on 2018/5/4.
 */
@Data
public class User implements Serializable {
    // 用户编号
    private Long userId;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 用户状态(0正常 1锁定 2废除)
    private Integer status;

    // 生成日期
    private Date gmtCreate;

    // 修改日期
    private Date gmtModified;
}
