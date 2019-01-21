package leetcode.array;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空数组，返回此数组中第三大的数。
 * 如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 */
public class No414ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        nums = removeDuplicates(nums);

        if (nums.length == 1) {
            return nums[0];
        }

        int order = 3;

        if (nums.length < order) {
            order = 1;
        }

        int start = 0, end = nums.length - 1;
        while (true) {
            int partition = partition(nums, start, end);
            if (partition == order - 1) {
                return nums[partition];
            }

            if (partition > order - 1) {
                end = partition - 1;
            } else {
                start = partition + 1;
            }
        }
    }

    private int[] removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int[] res = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            res[i++] = num;
        }

        return res;
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
     * 输入: [3, 2, 1]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 第三大的数是 1.
     */
    @Test
    public void testCase1() {
        int[] data = new int[]{3, 2, 1};
        System.out.println(thirdMax(data));
    }

    /**
     * 输入: [1, 2]
     * <p>
     * 输出: 2
     * <p>
     * 解释: 第三大的数不存在, 所以返回最大的数 2 .
     */
    @Test
    public void testCase2() {
        int[] data = new int[]{1, 2};
        System.out.println(thirdMax(data));
    }

    /**
     * 输入: [2, 2, 3, 1]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
     * 存在两个值为2的数，它们都排第二。
     */
    @Test
    public void testCase3() {
        int[] data = new int[]{2, 2, 3, 1};
        System.out.println(thirdMax(data));
    }

    /**
     * 输入: [1,1,1]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
     * 存在两个值为2的数，它们都排第二。
     */
    @Test
    public void testCase4() {
        int[] data = new int[]{1, 1, 1};
        System.out.println(thirdMax(data));
    }
}
