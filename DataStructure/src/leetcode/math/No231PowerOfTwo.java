package leetcode.math;

import org.junit.Test;

public class No231PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return isPowerOfTwo3(n);
    }

    public boolean isPowerOfTwo1(int n) {
        if (n <= 0) {
            return false;
        }

        while (n / 2 != 0) {
            if (n % 2 != 0) {
                return false;
            }

            n /= 2;
        }

        return true;
    }


    private boolean isPowerOfTwo2(int n) {
        double val = Math.log10(n) / Math.log10(2);
        System.out.println(String.valueOf(val));
        return String.valueOf(val)
                .matches("^\\d+\\.0$");
    }

    private boolean isPowerOfTwo3(int n) {
        return (Math.log10(n) / Math.log10(2)) % 1 == 0;
    }

    @Test
    public void testCase1() {
        System.out.println(isPowerOfTwo(8));
    }
}
