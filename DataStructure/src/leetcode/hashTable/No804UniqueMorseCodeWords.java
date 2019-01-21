package leetcode.hashTable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

public class No804UniqueMorseCodeWords {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
                "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...",
                "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new TreeSet<>();

        for (String word : words) {

            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }

    /**
     * 例如:
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * 输出: 2
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * <p>
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     */
    @Test
    public void testCase1() {
        int expectResult = 2;
        int result = uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"});

        Assert.assertEquals(expectResult, result);
    }
}
