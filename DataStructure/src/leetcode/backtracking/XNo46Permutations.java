package leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class XNo46Permutations {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> cur = new ArrayList<>();
        helper(cur, nums, 0, nums.length);
        return res;
    }

    private void helper(List<Integer> cur, int[] nums, int pos, int dataLength) {
        if (pos == dataLength) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < dataLength; i++) {
            if (cur.contains(nums[i])) {
                continue;
            }

            cur.add(nums[i]);

            helper(cur, nums, pos + 1, dataLength);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */
    @Test
    public void testCase1() {
        List<List<Integer>> result = permute(new int[]{1, 2, 3});
    }
}
