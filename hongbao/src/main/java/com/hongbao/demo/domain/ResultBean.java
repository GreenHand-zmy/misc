package com.hongbao.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zmy on 2018/5/4.
 */
@Data
public class ResultBean<T> implements Serializable {
    private static final String SUCCESS_CODE = "0";
    private static final String FAIL_CODE = "1";
    private String code;
    private String message;
    private T data;

    public ResultBean success() {
        code = SUCCESS_CODE;
        message = "success";
        return this;
    }

    public ResultBean success(String message) {
        code = SUCCESS_CODE;
        this.message = message;
        return this;
    }

    public ResultBean success(T data) {
        code = SUCCESS_CODE;
        message = "success";
        this.data = data;
        return this;
    }

    public ResultBean fail(String message) {
        code = FAIL_CODE;
        this.message = message;
        return this;
    }
}
