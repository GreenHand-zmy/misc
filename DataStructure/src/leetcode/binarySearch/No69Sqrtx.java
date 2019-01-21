package leetcode.binarySearch;

import org.junit.Test;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 */
public class No69Sqrtx {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 0, right = x / 2 + 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sqrt = x / mid;

            if (sqrt < mid) {
                right = mid - 1;
            } else if (sqrt > mid) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return right;
    }

    /**
     * 输入: 4
     * 输出: 2
     */
    @Test
    public void testCase1() {
        System.out.println(mySqrt(4));
    }

    /**
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     */
    @Test
    public void testCase2() {
        System.out.println(mySqrt(8));
    }

    @Test
    public void testCase3() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println(mySqrt(i));
        }
    }

    @Test
    public void testCase4() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println((int) Math.sqrt(i));
        }
    }
}
