package leetcode.twoPointers;

import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 */
public class No125ValidPalindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();

        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left <= s.length() - 1 && !accept(s.charAt(left))) {
                left++;
            }
            while (right >= 0 && !accept(s.charAt(right))) {
                right--;
            }

            // 字符越界相当于空字符串返回true
            if (left > s.length() - 1 || right < 0) {
                return true;
            } else if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private boolean accept(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }

    /**
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     */
    @Test
    public void testCase1() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    /**
     * 输入: "race a car"
     * 输出: false
     */
    @Test
    public void testCase2() {
        System.out.println(isPalindrome("race a car"));
    }

    /**
     * 输入: ".,"
     * 输出: true
     */
    @Test
    public void testCase3() {
        System.out.println(isPalindrome(".,"));
    }
}
