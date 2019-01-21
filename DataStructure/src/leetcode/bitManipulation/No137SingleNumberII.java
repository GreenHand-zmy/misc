package leetcode.bitManipulation;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class No137SingleNumberII {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }

    /**
     * 输入: [2,2,3,2]
     * 输出: 3
     */
    @Test
    public void testCase1() {
        int[] input = new int[]{2, 2, 3, 2};
        int expectResult = 3;

        int result = singleNumber(input);
        Assert.assertEquals(expectResult, result);
    }

    /**
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     */
    @Test
    public void testCase2() {
        int[] input = new int[]{0, 1, 0, 1, 0, 1, 99};
        int expectResult = 99;

        int result = singleNumber(input);
        Assert.assertEquals(expectResult, result);
    }
}
