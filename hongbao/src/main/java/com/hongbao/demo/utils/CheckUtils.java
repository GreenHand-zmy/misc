package com.hongbao.demo.utils;

public final class CheckUtils {
    /**
     * 判断条件是否成立,不成立抛出运行时异常
     *
     * @param expectCondition 希望成立的条件
     * @param messages        条件不成立时,输出的信息
     */
    public static void check(boolean expectCondition, String messages) {
        if (!expectCondition) {
            fail(messages);
        }
    }

    /**
     * 抛出一个带有信息的运行时异常
     *
     * @param messages 异常信息
     */
    private static void fail(String messages) {
        throw new RuntimeException(messages);
    }
}
