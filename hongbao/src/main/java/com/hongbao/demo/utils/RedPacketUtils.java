package com.hongbao.demo.utils;

public final class RedPacketUtils {
    /**
     * 生成开头为181,后8位为随机数的号码
     *
     * @return
     */
    public static String randomPhone() {
        return "181" + StringUtils.randomString('1', '9', 8);
    }
}
