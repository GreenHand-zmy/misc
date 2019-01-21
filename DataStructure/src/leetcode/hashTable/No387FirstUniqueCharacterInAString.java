package leetcode.hashTable;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 */
public class No387FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }
        }


        return -1;
    }

    /**
     * s = "leetcode"
     * 返回 0.
     */
    @Test
    public void testCase1() {
        String input = "leetcode";
        System.out.println(firstUniqChar(input));
    }

    /**
     * s = "loveleetcode",
     * 返回 2.
     */
    @Test
    public void testCase2() {
        String input = "loveleetcode";
        System.out.println(firstUniqChar(input));
    }
}
