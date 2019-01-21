package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

public final class CastUtil {
    /**
     * 转为String型(默认为空)
     *
     * @param object
     * @return
     */
    public static String castString(Object object) {
        return CastUtil.castString(object, "");
    }

    /**
     * 转为String类型(提供默认值)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转化为double类型(提供默认值)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String stringValue = CastUtil.castString(object);
            if (StringUtils.isNotEmpty(stringValue)) {
                doubleValue = Double.parseDouble(stringValue);
            }
        }
        return doubleValue;
    }

    /**
     * 转化为double类型(默认值为0)
     *
     * @param object
     * @return
     */
    public static double castDouble(Object object) {
        return CastUtil.castDouble(object, 0);
    }

    /**
     * 转化为long类型
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (object != null) {
            String stringValue = CastUtil.castString(object);
            if (StringUtils.isNotEmpty(stringValue)) {
                longValue = Long.parseLong(stringValue);
            }
        }
        return longValue;
    }

    /**
     * 转化为long类型(默认值为0)
     *
     * @param object
     * @return
     */
    public static long castLong(Object object) {
        return CastUtil.castLong(object, 0L);
    }

    /**
     * 转化为int类型(默认值0)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String stringValue = CastUtil.castString(object);
            if (StringUtils.isNotEmpty(stringValue)) {
                intValue = Integer.parseInt(stringValue);
            }
        }
        return intValue;
    }

    /**
     * 转化为int类型默认为0
     *
     * @param object
     * @return
     */
    public static int castInt(Object object) {
        return castInt(object, 0);
    }

    /**
     * 转化为boolean类型(提供默认值)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }

    /**
     * 转化为boolean类型(默认值为false)
     *
     * @param object
     * @return
     */
    public static boolean castBoolean(Object object) {
        return CastUtil.castBoolean(object, false);
    }
}
