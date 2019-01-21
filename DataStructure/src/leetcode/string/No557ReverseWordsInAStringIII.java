package leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class No557ReverseWordsInAStringIII {
    public String reverseWords(String s) {
        return reverseWords2(s);
    }

    public String reverseWords1(String s) {
        s = s + " ";
        char[] chars = s.toCharArray();

        int currentPosition = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = s.charAt(i);

            if (ch == ' ') {
                reverse(chars, currentPosition, i - 1);
                currentPosition = i + 1;
            }
        }

        return new String(chars).trim();
    }

    private void reverse(char[] chars, int begin, int end) {
        while (begin < end) {
            swap(chars, begin++, end--);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public String reverseWords2(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
    }

    /**
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc"
     */
    @Test
    public void testCase1() {
        String input = "Let's take LeetCode contest";
        String expectResult = "s'teL ekat edoCteeL tsetnoc";

        String result = reverseWords(input);

        Assert.assertEquals(expectResult, result);
    }
}
