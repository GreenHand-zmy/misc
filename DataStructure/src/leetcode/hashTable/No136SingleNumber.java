package leetcode.hashTable;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class No136SingleNumber {
    public int singleNumber(int[] nums) {
        return singleNumber2(nums);
    }

    private int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }

    private int singleNumber2(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            result = result ^ num;
        }

        return result;
    }

    /**
     * 输入: [2,2,1]
     * 输出: 1
     */
    @Test
    public void testCase1() {
        int[] input = new int[]{2, 2, 1};
        int expectResult = 1;

        int result = singleNumber(input);

        Assert.assertEquals(expectResult, result);
    }

    /**
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    @Test
    public void testCase2() {
        int[] input = new int[]{4, 1, 2, 1, 2};
        int expectResult = 4;

        int result = singleNumber(input);

        Assert.assertEquals(expectResult, result);
    }
}
