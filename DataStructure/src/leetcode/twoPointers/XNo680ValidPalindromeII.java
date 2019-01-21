package leetcode.twoPointers;

import org.junit.Test;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 */
public class XNo680ValidPalindromeII {
    public boolean validPalindrome(String s) {
        return validPalindrome2(s);
    }

    public boolean validPalindrome2(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                int j = s.length() - i - 1;
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    public boolean validPalindrome1(String s) {
        // 如果本身就是回文串,不需要删去字符
        if (isPalindrome(s)) {
            return true;
        }

        // 如果本身不是回文串,删去一个字符再判断是否为回文
        for (int skip = 0; skip < s.length(); skip++) {

            // 跳过一个字符
            char[] chars = new char[s.length() - 1];
            for (int i = 0; i < skip; i++) {
                chars[i] = s.charAt(i);
            }
            for (int i = skip + 1; i < s.length(); i++) {
                chars[i - 1] = s.charAt(i);
            }

            if (isPalindrome(new String(chars))) {
                return true;
            }
        }

        return false;
    }

    private boolean isPalindrome(String str, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (str.charAt(k) != str.charAt(j - k + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


    /**
     * 输入: "aba"
     * 输出: True
     */
    @Test
    public void testCase1() {
        String input = "aba";
        System.out.println(validPalindrome(input));
    }

    /**
     * 输入: "abca"
     * 输出: True
     * 解释: 你可以删除c字符。
     */
    @Test
    public void testCase2() {
        String input = "abca";
        System.out.println(validPalindrome(input));
    }
}
