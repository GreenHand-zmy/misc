package leetcode.math;

import org.junit.Test;

public class No7ReverseInt {

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;

            // 上界
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }
            // 下界
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }
            result = result * 10 + temp;
        }
        return result;
    }

    @Test
    public void testCase1() {
        System.out.println(reverse(200));
    }

}


