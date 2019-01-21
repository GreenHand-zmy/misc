package com.hongbao.demo.mock;

import lombok.Data;

@Data
public class MockCookieInfo {
    private String cookieName;
    private Integer modified;

    public MockCookieInfo(String cookieName, Integer modified) {
        this.cookieName = cookieName;
        this.modified = modified;
    }
}
