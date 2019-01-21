package leetcode.dp;

import org.junit.Test;

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 */
public class No303RangeSumQueryImmutable {
    class NumArray {
        /*private int[][] rangeArray;

        public NumArray(int[] nums) {
            rangeArray = new int[nums.length][nums.length];
            for (int i = 0; i < nums.length; i++) {
                rangeArray[i][i] = nums[i];
            }

            buildRangeArray(nums);
        }

        private void buildRangeArray(int[] nums) {
            for (int start = 0; start < nums.length - 1; start++) {
                for (int end = start; end < nums.length; end++) {
                    if (start != end) {
                        rangeArray[start][end] = rangeArray[start][end - 1] + rangeArray[end][end];
                    }
                }
            }
        }

        public int sumRange(int i, int j) {
            if (i > j) {
                throw new IllegalArgumentException("i不能大于j");
            }

            return rangeArray[i][j];
        }*/
        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

    /**
     * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
     * <p>
     * sumRange(0, 2) -> 1
     * sumRange(2, 5) -> -1
     * sumRange(0, 5) -> -3
     */
    @Test
    public void testCase1() {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
