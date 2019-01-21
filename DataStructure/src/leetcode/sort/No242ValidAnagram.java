package leetcode.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 */
public class No242ValidAnagram {
    public boolean isAnagram(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();


        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    /**
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    @Test
    public void testCase1() {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    /**
     * 输入: s = "rat", t = "car"
     * 输出: false
     */
    @Test
    public void testCase2() {
        String s = "rat", t = "car";
        System.out.println(isAnagram(s, t));
    }
}
