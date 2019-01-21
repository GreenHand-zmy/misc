package leetcode.math;

public class No9Palindrome {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;

            // 溢出检测
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }

            result = result * 10 + temp;
        }
        return result;
    }

    public boolean isPalindrome(int x) {
        return isPalindrome2(x);
    }

    private boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        int reverseNumber = reverse(x);
        return reverseNumber == x;
    }

    private boolean isPalindrome2(int x) {
        // 当遇到最后一位为0,为了使该数字为0,则数字开头也必须为0,只有0符合
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int result = 0;
        while (result < x) {
            int pop = x % 10;
            x = x / 10;
            result = result * 10 + pop;
        }
        System.out.println("x: " + x);
        System.out.println("result: " + result);

        // 当 x 数字的长度为奇数,可以简单将反转结果除以10,去掉中位数,因为中间的数字总是等于自己
        // 例如x = 12321,在while循环后x=12,result=123,result / 10 = 12。
        return x == result || x == result / 10;
    }

    public static void main(String[] args) {
        No9Palindrome run = new No9Palindrome();
        System.out.println(run.isPalindrome(1000));
    }
}
