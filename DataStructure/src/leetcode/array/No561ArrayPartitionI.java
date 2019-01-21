package leetcode.array;

import org.junit.Test;

import java.util.Arrays;

public class No561ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int minMax = 0;
        for (int i = 0; i < nums.length; i += 2) {
            minMax += nums[i];
        }
        return minMax;
    }

    /**
     * 输入: [1,4,3,2]
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     */
    @Test
    public void testCase1() {
        System.out.println(arrayPairSum(new int[]{1, 4, 3, 2}));
    }
}
