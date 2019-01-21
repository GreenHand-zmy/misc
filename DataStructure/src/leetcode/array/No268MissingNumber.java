package leetcode.array;

import org.junit.Assert;
import org.junit.Test;

public class No268MissingNumber {
    public int missingNumber(int[] nums) {
        return missingNumber1(nums);
    }

    public int missingNumber1(int[] nums) {
        boolean[] nonMissing = new boolean[nums.length + 1];

        for (int num : nums) {
            nonMissing[num] = true;
        }

        for (int num = 0; num <= nums.length; num++) {
            if (!nonMissing[num]) {
                return num;
            }
        }

        return -1;
    }

    public int missingNumber2(int[] nums) {
        int sum = 0;
        int n = nums.length;

        for (int num : nums) {
            sum += num;
        }
        return ((1 + n) * n) / 2 - sum;

    }

    /**
     * 输入: [3,0,1]
     * 输出: 2
     */
    @Test
    public void testCase1() {
        int expectResult = 2;

        int result = missingNumber(new int[]{3, 0, 1});

        Assert.assertEquals(expectResult, result);
    }

    /**
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     */
    @Test
    public void testCase2() {
        int expectResult = 8;

        int result = missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});

        Assert.assertEquals(expectResult, result);
    }

    /**
     * 输入: [1]
     * 输出: 0
     */
    @Test
    public void testCase3() {
        int expectResult = 0;

        int result = missingNumber(new int[]{1});

        Assert.assertEquals(expectResult, result);
    }

    /**
     * 输入: [0,1]
     * 输出: 2
     */
    @Test
    public void testCase4() {
        int expectResult = 2;

        int result = missingNumber(new int[]{0, 1});

        Assert.assertEquals(expectResult, result);
    }
}
