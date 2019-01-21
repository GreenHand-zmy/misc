package leetcode.string;

import javafx.concurrent.Worker;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 1.全部字母都是大写，比如"USA"。
 * 2.单词中所有字母都不是大写，比如"leetcode"。
 * 3.如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 */
public class No520DetectCapital {
    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();
        return moreTwoAndFirstCapital(chars) ||
                isAllCapital(chars, 0, word.length() - 1) || isAllLowercase(chars, 0, word.length() - 1);

    }

    private boolean isAllLowercase(char[] chars, int begin, int end) {

        for (int i = begin; i <= end; i++) {
            char ch = chars[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllCapital(char[] chars, int begin, int end) {

        for (int i = begin; i <= end; i++) {
            char ch = chars[i];
            if (!(ch >= 'A' && ch <= 'Z')) {
                return false;
            }
        }
        return true;
    }

    private boolean moreTwoAndFirstCapital(char[] chars) {
        return chars.length >= 2 && isAllLowercase(chars, 1, chars.length - 1);
    }

    /**
     * 输入: "USA"
     * 输出: True
     */
    @Test
    public void testCase1() {
        String input = "USA";
        boolean exceptResult = true;

        boolean result = detectCapitalUse(input);

        Assert.assertEquals(exceptResult, result);
    }

    /**
     * 输入: "FlaG"
     * 输出: False
     */
    @Test
    public void testCase2() {
        String input = "FlaG";
        boolean exceptResult = false;

        boolean result = detectCapitalUse(input);

        Assert.assertEquals(exceptResult, result);
    }

    /**
     * 输入: "Ca"
     * 输出: true
     */
    @Test
    public void testCase3() {
        String input = "Ca";
        boolean exceptResult = true;

        boolean result = detectCapitalUse(input);

        Assert.assertEquals(exceptResult, result);
    }
}
