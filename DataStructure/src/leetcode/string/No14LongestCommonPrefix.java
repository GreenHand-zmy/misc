package leetcode.string;

import java.util.Arrays;
import java.util.Comparator;

public class No14LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        return longestCommonPrefix2(strs);
    }

    public String longestCommonPrefix1(String[] strs) {
        StringBuilder result = new StringBuilder();

        // 处理边界情况
        if (strs == null || strs.length == 0) {
            return result.toString();
        } else if (strs.length == 1) {
            return result.append(strs[0]).toString();
        }

        // 将字符串数组排序
        Arrays.sort(strs, Comparator.comparingInt(String::length));

        char[][] chars = new char[strs.length][];

        // 将字符串转换为字符序列
        for (int i = 0; i < strs.length; i++) {
            chars[i] = strs[i].toCharArray();
        }

        // 外层循环比较次数(最短字符序列)
        for (int column = 0; column < strs[0].length(); column++) {
            char compare = chars[0][column];
            // 内层循环(需要比较的字符串)
            for (int row = 1; row < strs.length; row++) {
                // 如果比较失败,则直接返回,因为是公共前缀
                if (compare != chars[row][column]) {
                    return result.toString();
                } else if (row == strs.length - 1) {    // 只有当所有行字符串比对通过,才添加到结果中去
                    result.append(compare);
                }
            }
        }

        return result.toString();
    }

    public String longestCommonPrefix2(String[] strs) {
        // 边界情况
        if (strs.length == 0) {
            return "";
        }

        // 将数组中第一个元素当作最长公共前缀
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {

            // 迭代两个字符串,直到找出公共前缀,找不到则返回空字符串
            while (strs[i].indexOf(prefix) != 0) {

                // 迭代过程,每次长度减1
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
//        String[] strings = "flower,flow,flight".split(",");
//        String[] strings = "dog,racecar,car".split(",");
        String[] strings = "leets,leetcode,leet,leeds".split(",");
        No14LongestCommonPrefix run = new No14LongestCommonPrefix();
        System.out.println(run.longestCommonPrefix(strings));
        System.out.println("leetcode".indexOf("leets"));
    }
}
