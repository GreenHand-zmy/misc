package leetcode.hashTable;

import org.junit.Test;

import java.util.*;

public class No500KeyboardRow {
    public String[] findWords(String[] words) {
        Map<Integer, String> KeyboardMap = new HashMap<>();
        String rowOne = "qwertyuiop";
        String rowTwo = "asdfghjkl";
        String rowThree = "zxcvbnm";

        KeyboardMap.put(1, rowOne);
        KeyboardMap.put(2, rowTwo);
        KeyboardMap.put(3, rowThree);

        List<String> result = new ArrayList<>();
        for (String word : words) {
            for (int row = 1; row <= 3; row++) {

                int index = 0;
                for (char c : word.toLowerCase().toCharArray()) {
                    if (KeyboardMap.get(row).indexOf(c) == -1) {
                        break;
                    }
                    index++;
                }

                if (index == word.length()) {
                    result.add(word);
                }
            }
        }
        String[] x = new String[result.size()];
        return  result.toArray(x);
    }

    /**
     * 输入: ["Hello", "Alaska", "Dad", "Peace"]
     * 输出: ["Alaska", "Dad"]
     */
    @Test
    public void testCase1() {
        String[] words = findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"});
    }
}
