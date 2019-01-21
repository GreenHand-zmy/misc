package com.hongbao.demo.concurrent;

import lombok.Data;

/**
 * Created by zmy on 2018/5/9.
 */
@Data
public class Cookie {
    private String cookieName;
    private Integer modifiedCount;

    public Cookie() {
    }

    public Cookie(String cookieName, Integer modifiedCount) {
        this.cookieName = cookieName;
        this.modifiedCount = modifiedCount;
    }
}
