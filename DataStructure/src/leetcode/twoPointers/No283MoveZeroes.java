package leetcode.twoPointers;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class No283MoveZeroes {
    public void moveZeroes(int[] nums) {
        moveZeroes2(nums);
    }

    /**
     * 复制补0法
     * 时间复杂度:O(n)
     * 空间复杂度:O(1)
     *
     * @param nums
     */
    private void moveZeroes2(int[] nums) {
        int start = 0, index = 0;
        int length = nums.length;

        // 复制非0数据
        while (index < length) {
            if (nums[index] != 0) {
                nums[start++] = nums[index];
            }
            index++;
        }

        // 结尾补0
        while (start < length) {
            nums[start++] = 0;
        }
    }

    /**
     * 冒泡法,遇0冒泡
     * 时间复杂度:O(n^2)
     * 空间复杂度:O(1)
     *
     * @param nums
     */
    private void moveZeroes1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            for (int j = i; j < nums.length; j++) {
                if (nums[i] == 0) {
                    swap(nums, i, j);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     */
    @Test
    public void testCase1() {
        int[] input = {0, 1, 0, 3, 12};
        moveZeroes(input);
        System.out.println(Arrays.toString(input));
    }
}
