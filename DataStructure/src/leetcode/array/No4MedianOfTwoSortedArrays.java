package leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * <p>
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * <p>
 * 你可以假设 nums1 和 nums2 不同时为空。
 */
public class No4MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int newLength = nums1.length + nums2.length;
        int[] copyedArray = new int[newLength];
        int[] sortedArray = new int[newLength];

        System.arraycopy(nums1, 0, copyedArray, 0, nums1.length);
        System.arraycopy(nums2, 0, copyedArray, nums1.length, nums2.length);

        int i = 0, j = nums1.length;
        for (int k = 0; k < copyedArray.length; k++) {
            if (i > nums1.length - 1) {
                sortedArray[k] = copyedArray[j++];
            } else if (j > copyedArray.length - 1) {
                sortedArray[k] = copyedArray[i++];
            } else if (copyedArray[i] < copyedArray[j]) {
                sortedArray[k] = copyedArray[i++];
            } else {
                sortedArray[k] = copyedArray[j++];
            }
        }

        double result;
        if (newLength % 2 == 0) {
            result = (sortedArray[newLength / 2 - 1] + sortedArray[newLength / 2]) / 2.0;
        } else {
            result = sortedArray[newLength / 2];
        }
        return result;
    }

    /**
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * 中位数是 2.0
     */
    @Test
    public void testCase1() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    /**
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
     * 中位数是 (2 + 3)/2 = 2.5
     */
    @Test
    public void testCase2() {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
