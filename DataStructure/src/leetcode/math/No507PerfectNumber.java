package leetcode.math;

import org.junit.Test;

public class No507PerfectNumber {
    public boolean checkPerfectNumber(int num) {
        return checkPerfectNumber2(num);
    }

    public boolean checkPerfectNumber1(int num) {
        if (num < 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
            if (sum > num) {
                return false;
            }
        }
        return sum == num;
    }

    public boolean checkPerfectNumber2(int num) {
        if (num <= 0) {
            return false;
        }

        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }

        return sum - num == num;
    }

    /**
     * 输入: 28
     * 输出: True
     * 解释: 28 = 1 + 2 + 4 + 7 + 14
     */
    @Test
    public void testCase1() {
        System.out.println(checkPerfectNumber(99999991));
    }
}
