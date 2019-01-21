package leetcode.math;

import org.junit.Test;

public class No258AddDigits {
    public int addDigits(int num) {
        num = digitsSum(num);

        while ((num / 10) != 0) {
            num = digitsSum(num);
        }

        return num;
    }

    private int digitsSum(int num) {
        int sum = 0;

        while (num != 0) {
            int digits = num % 10;
            num /= 10;

            sum += digits;
        }
        return sum;
    }

    /**
     * I'll try to explain the math behind this:
     *
     * First you should understand:
     *
     * 10^k % 9 = 1
     * a*10^k % 9 = a % 9
     * Then let's use an example to help explain.
     *
     * Say a number x = 23456
     *
     * x = 2* 10000 + 3 * 1000 + 4 * 100 + 5 * 10 + 6
     *
     * 2 * 10000 % 9 = 2 % 9
     *
     * 3 * 1000 % 9 = 3 % 9
     *
     * 4 * 100 % 9 = 4 % 9
     *
     * 5 * 10 % 9 = 5 % 9
     *
     * Then x % 9 = ( 2+ 3 + 4 + 5 + 6) % 9, note that x = 2* 10000 + 3 * 1000 + 4 * 100 + 5 * 10 + 6
     *
     * So we have 23456 % 9 = (2 + 3 + 4 + 5 + 6) % 9
     * @param num
     * @return
     */
    public int addDigits1(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }

    /**
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     */
    @Test
    public void testCase1() {
        System.out.println(addDigits1(38));
    }
}
