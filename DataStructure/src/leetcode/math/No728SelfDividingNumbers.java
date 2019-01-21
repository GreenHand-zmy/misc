package leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No728SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> selfDividingNumbers = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumber(i)) {
                selfDividingNumbers.add(i);
            }
        }
        return selfDividingNumbers;
    }

    private boolean isSelfDividingNumber(int number) {
        int tempNumber = number;

        while (number != 0) {
            int current = number % 10;
            if (current == 0) {
                return false;
            }

            number = number / 10;

            if (tempNumber % current != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入：
     * 上边界left = 1, 下边界right = 22
     * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     */
    @Test
    public void testCase1() {
        System.out.println(selfDividingNumbers(1, 22));
    }
}
