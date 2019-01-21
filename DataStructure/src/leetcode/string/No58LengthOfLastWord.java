package leetcode.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 */
public class No58LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String[] res = s.split(" ");
        return res.length > 0 ? res[res.length - 1].length() : 0;
    }

    private boolean isCharacter(char ch) {
        if (ch <= 'z' && ch >= 'a') {
            return true;
        } else return ch <= 'Z' && ch >= 'A';
    }

    @Test
    public void testCase1() {
        System.out.println(lengthOfLastWord("a "));
        System.out.println(Arrays.toString(" ".split(" ")));
    }
}
