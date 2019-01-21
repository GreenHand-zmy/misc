package com.hongbao.demo.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 对应修改手机号的请求
 * <p>
 * Created by zmy on 2018/5/4.
 */
@Data
public class ChangPhoneRequest implements Serializable {
    // 用户签名
    private String sign;

    // 更改的手机号
    private String phone;
}
