package leetcode.math;

import com.sun.tools.javac.Main;
import org.junit.Test;

/**
 * 实现 atoi，将字符串转为整数。
 * <p>
 * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。
 * 如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，
 * 这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
 * <p>
 * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
 * <p>
 * 若函数不能执行有效的转换，返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。
 * 如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 */
public class No8StringToIntegerAtoi {
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;

        // 1.Empty String
        if (str.length() == 0) {
            return 0;
        }

        // 2.Remove Spaces
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        if (index == str.length()) {
            return 0;
        }

        // 3.Handle signs
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // 4.convert number and avoid overflow
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }

            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = 10 * total + digit;
            index++;
        }
        return total * sign;
    }

    @Test
    public void testCase1() {
        System.out.println(myAtoi(" -42"));
    }
}
