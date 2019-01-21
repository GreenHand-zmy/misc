package leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class No13RomanToInt {

    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>(7);
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        char[] romanNumber = s.toCharArray();

        int result = 0;
        for (int index = 0; index < romanNumber.length; index++) {
            char c = romanNumber[index];

            Integer romanInt = romanMap.get(c);
            result = result + romanInt;
            // 处理边界情况 例如III
            if (index < romanNumber.length - 1) {
                // 上面一股脑都加了,所以要减去两倍
                if (c == 'I' && (romanNumber[index + 1] == 'V' || romanNumber[index + 1] == 'X')) {
                    result = result - 2;
                }
                if (c == 'X' && (romanNumber[index + 1] == 'L' || romanNumber[index + 1] == 'C')) {
                    result = result - 20;
                }
                if (c == 'C' && (romanNumber[index + 1] == 'D' || romanNumber[index + 1] == 'M')) {
                    result = result - 200;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        No13RomanToInt run = new No13RomanToInt();
        int roman = run.romanToInt("DM");
        System.out.println(roman);

    }
}


