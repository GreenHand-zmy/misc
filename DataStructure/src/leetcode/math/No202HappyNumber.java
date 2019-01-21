package leetcode.math;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class No202HappyNumber {
    public boolean isHappy(int n) {
        return isHappy3(n);
    }

    /**
     * 时间超限,会死循环
     *
     * @param n
     * @return
     */
    private boolean isHappy1(int n) {
        n = digitsSum(n);

        while (n != 1) {
            n = digitsSum(n);
        }
        return true;
    }

    /**
     * 快慢指针,环检测
     *
     * @param n
     * @return
     */
    private boolean isHappy2(int n) {
        int fast = n, slow = n;

        do {
            fast = digitsSum(digitsSum(fast));
            slow = digitsSum(slow);

            if (fast == slow) {
                return fast == 1;
            }
        } while (true);
    }

    /**
     * 记录重复
     *
     * @param n
     * @return
     */
    private boolean isHappy3(int n) {
        Set<Integer> set = new HashSet<>();
        do {
            n = digitsSum(n);
            if (!set.contains(n)) {
                set.add(n);
            } else {
                return false;
            }
        } while (n != 1);

        return true;
    }

    private int digitsSum(int num) {
        int sum = 0;

        while (num != 0) {
            int digits = num % 10;
            num /= 10;

            sum += digits * digits;
        }
        return sum;
    }

    /**
     * 输入: 19
     * 输出: true
     * 解释:
     * 1^2 + 9^2 = 8
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    @Test
    public void testCase1() {
        System.out.println(isHappy(2));
    }
}
