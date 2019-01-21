package leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回所有可能得到的字符串集合。
 */
public class XNo784LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        compute(ans, S.toCharArray(), 0);
        return ans;
    }

    public void compute(List<String> ans, char[] chars, int index) {
        if (index == chars.length) {
            ans.add(new String(chars));
        } else {
            if (Character.isLetter(chars[index])) {
                chars[index] = Character.toLowerCase(chars[index]);
                compute(ans, chars, index + 1);
                chars[index] = Character.toUpperCase(chars[index]);
            }
            compute(ans, chars, index + 1);
        }
    }

    /**
     * 示例:
     * 输入: S = "a1b2"
     * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
     * <p>
     * 输入: S = "3z4"
     * 输出: ["3z4", "3Z4"]
     * <p>
     * 输入: S = "12345"
     * 输出: ["12345"]
     */
    @Test
    public void testCase1() {
        List<String> list = letterCasePermutation("asdasdasdfds");
    }
}
