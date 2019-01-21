package leetcode.math;

import org.junit.Test;

/**
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 */
public class No504Base7 {
    public String convertToBase7(int num) {
        boolean isNegative = false;
        StringBuilder res = new StringBuilder();

        if (num == 0) {
            res.append(0);
            return res.toString();
        }

        if (num < 0) {
            isNegative = true;
            num = -num;
        }
        while (num != 0) {
            int mod = num % 7;
            num = num / 7;
            res.insert(0, mod);
        }

        if (isNegative) {
            res.insert(0, '-');
        }
        return res.toString();
    }

    /**
     * 输入: 100
     * 输出: "202"
     */
    @Test
    public void testCase1() {
        System.out.println(convertToBase7(100));
    }

    /**
     * 输入: -7
     * 输出: "-10"
     */
    @Test
    public void testCase2() {
        System.out.println(convertToBase7(-7));
    }

    /**
     * 输入：
     * -8
     * 预期：
     * "-11"
     */
    @Test
    public void testCase3() {
        System.out.println(convertToBase7(-8));
    }
}
