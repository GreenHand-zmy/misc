package leetcode.hashTable;

import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 */
public class No349IntersectionOfTwoArrays {
    // 很low
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> res = new ArrayList<>();

        for (Integer num : set1) {
            if (set2.contains(num)) {
                res.add(num);
            }
        }

        int[] ret = new int[res.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = res.get(i);
        }

        return ret;
    }

    /**
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     */
    @Test
    public void testCase() {
        int[] input1 = new int[]{1, 2, 2, 1};
        int[] input2 = new int[]{2, 2};

        int[] exceptResult = new int[]{2};

        int[] result = intersection(input1, input2);

        Assert.assertEquals(exceptResult, result);
    }
}
