package leetcode.hashTable;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No350IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                Integer times = map.get(num);

                if (times == 1) {
                    map.remove(num);
                } else {
                    map.put(num, times - 1);
                }

                list.add(num);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     */
    @Test
    public void testCase1() {
        int[] input1 = new int[]{1, 2, 2, 1};
        int[] input2 = new int[]{2, 2};

        int[] exceptResult = new int[]{2, 2};

        int[] result = intersect(input1, input2);

        Assert.assertEquals(exceptResult, result);
    }

    /**
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     */
    @Test
    public void testCase2() {
        int[] input1 = new int[]{4, 9, 5};
        int[] input2 = new int[]{9, 4, 9, 8, 4};

        int[] exceptResult = new int[]{4, 9};

        int[] result = intersect(input1, input2);

        Assert.assertEquals(exceptResult, result);
    }
}
