package leetcode.array;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class XNo215KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }

        int start = 0, end = nums.length - 1;
        while (true) {
            if (start >= end) {
                return nums[end];
            }

            int partition = partition(nums, start, end);
            if (partition == k - 1) {
                return nums[partition];
            }

            if (partition > k - 1) {
                end = partition - 1;
            } else {
                start = partition + 1;
            }
        }
    }

    private int partition(int[] data, int start, int end) {
        int v = data[start];

        int left = start, right = end + 1;
        while (true) {
            while (data[++left] > v) {
                if (left == end) {
                    break;
                }
            }
            while (data[--right] < v) {
                if (right == start) {
                    break;
                }
            }

            if (left >= right) {
                break;
            }
            swap(data, left, right);
        }

        swap(data, start, right);
        return right;
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     */
    @Test
    public void testCase1() {
        int[] data = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(data, 2));
    }

    /**
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     */
    @Test
    public void testCase2() {
        int[] data = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(data, 4));
    }

    /**
     * 输入: [2,1] 和 k = 2
     * 输出: 1
     */
    @Test
    public void testCase3() {
        int[] data = new int[]{2, 1};
        System.out.println(findKthLargest(data, 2));
    }
}
