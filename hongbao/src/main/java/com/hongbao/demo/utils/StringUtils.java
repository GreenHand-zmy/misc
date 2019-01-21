package com.hongbao.demo.utils;

import java.util.Random;

/**
 * Created by zmy on 2018/5/4.
 */
public final class StringUtils {
    /**
     * 返回自定义的随机字符串
     *
     * @param start  随机字符的开始范围
     * @param end    随机字符的结束范围
     * @param length 需要生成多少字符
     * @return
     */
    public static String randomString(char start, char end, int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char ch = (char) (random.nextInt(end - start + 1) + start);
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     * 判断是非为手机号
     *
     * @param phone 手机号
     * @return
     */
    public static boolean isPhoneNumber(String phone) {
        return !isEmpty(phone) && phone.length() == 11;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
