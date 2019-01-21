package leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class No66PlusOne {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int[] copyOfDigits = Arrays.copyOf(digits, length);

        copyOfDigits[length - 1] += 1;

        for (int i = length - 1; i > 0; i--) {
            if (copyOfDigits[i] > 9) {
                copyOfDigits[i - 1] += 1;
                copyOfDigits[i] = 0;
            }
        }

        // 如果最高位,大于10,数组溢出,重新拷贝
        if (copyOfDigits[0] == 10) {
            int[] newCopyOfDigits = new int[length + 1];
            System.arraycopy(copyOfDigits, 0, newCopyOfDigits, 1, length);

            newCopyOfDigits[1] = 0;
            newCopyOfDigits[0] = 1;

            return newCopyOfDigits;
        }
        return copyOfDigits;
    }

    @Test
    public void testCase1() {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
    }

    @Test
    public void testCase2() {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9})));
    }
}
