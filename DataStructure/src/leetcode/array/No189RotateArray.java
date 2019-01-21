package leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 */
public class No189RotateArray {
    public void rotate(int[] nums, int k) {
        rotate3(nums, k);
    }

    private void rotate1(int[] nums, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }

        k = k % nums.length;
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    private void rotate2(int[] nums, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }

        k = k % nums.length;
        int[] array = new int[nums.length];
        System.arraycopy(nums, nums.length - k, array, 0, k);
        System.arraycopy(nums, 0, array, k, nums.length - k);

        System.arraycopy(array, 0, nums, 0, nums.length);
    }

    private void rotate3(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }
    /**
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     */
    @Test
    public void testCase1() {
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(input, 3);
        System.out.println(Arrays.toString(input));
    }

    /**
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */
    @Test
    public void testCase2() {

    }
}
