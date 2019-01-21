package leetcode.string;

import org.junit.Assert;
import org.junit.Test;

public class No344ReverseString {
    public String reverseString(String s) {
        char[] chars = s.toCharArray();

        int begin = 0, end = chars.length - 1;
        while (begin < end) {
            swap(chars, begin++, end--);
        }

        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    /**
     * 输入: "hello"
     * 输出: "olleh"
     */
    @Test
    public void testCase1() {
        String exceptResult = "olleh";
        String result = reverseString("hello");

        Assert.assertEquals(exceptResult, result);
    }
    /**
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: "amanaP :lanac a ,nalp a ,nam A"
     */
    @Test
    public void testCase2() {
        String exceptResult = "amanaP :lanac a ,nalp a ,nam A";
        String result = reverseString("A man, a plan, a canal: Panama");

        Assert.assertEquals(exceptResult, result);
    }
}
