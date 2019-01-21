package com.peter.openx.util;

public final class CheckUtil {
    public static void check(boolean condition, String msg) {
        if (!condition) {
            fail(msg);
        }
    }

    private static void fail(String msg) {
        throw new RuntimeException(msg);
    }
}
