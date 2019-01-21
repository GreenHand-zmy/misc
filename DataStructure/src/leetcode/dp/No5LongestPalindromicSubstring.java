package leetcode.dp;

import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 */
public class No5LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        return longestPalindrome1(s);
    }

    public String longestPalindrome1(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public String longestPalindrome2(String s) {
        char[] chars = s.toCharArray();

        for (int length = s.length(); length > 0; length--) {
            for (int j = 0; j <= s.length() - length; j++) {
                if (isPalindromic(chars, j, length + j - 1)) {
                    return new String(chars, j, length);
                }
            }
        }
        return "";
    }

    private boolean isPalindromic(char[] chars, int start, int end) {
        if (chars.length == 0) {
            return false;
        }

        int i = start, j = end;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    /**
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba"也是一个有效答案。
     */
    @Test
    public void testCase1() {
        System.out.println(longestPalindrome("babad"));
    }

    /**
     * 输入: "cbbd"
     * 输出: "bb"
     */
    @Test
    public void testCase2() {
        System.out.println(longestPalindrome("cbbd"));
    }
}
