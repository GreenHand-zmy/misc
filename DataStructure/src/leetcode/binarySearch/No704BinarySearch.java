package leetcode.binarySearch;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class No704BinarySearch {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     */
    @Test
    public void testCase1() {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};

        int position = search(nums, 9);

        Assert.assertEquals(4, position);
    }

    /**
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     */
    @Test
    public void testCase2() {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};

        int position = search(nums, 2);

        Assert.assertEquals(-1, position);
    }
}
