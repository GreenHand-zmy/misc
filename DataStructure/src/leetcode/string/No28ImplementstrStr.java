package leetcode.string;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 */
public class No28ImplementstrStr {
    public int strStr(String haystack, String needle) {
        return strStr1(haystack, needle);
    }

    public int strStr1(String haystack, String needle) {
        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int k = i;
            int j = 0;
            for (; j < needle.length(); j++) {
                if (haystack.charAt(k++) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     */
    @Test
    public void testCase1() {
        String input1 = "hello";
        String input2 = "ll";

        System.out.println(strStr(input1, input2));
    }

    /**
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     */
    @Test
    public void testCase2() {
        String input1 = "aaaaa";
        String input2 = "bba";

        System.out.println(strStr(input1, input2));
    }

    /**
     * 输入: haystack = "a", needle = "a"
     * 输出: 0
     */
    @Test
    public void testCase3() {
        String input1 = "a";
        String input2 = "a";

        System.out.println(strStr(input1, input2));
    }
}
