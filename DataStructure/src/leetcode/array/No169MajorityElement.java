package leetcode.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class No169MajorityElement {
    public int majorityElement(int[] nums) {
        return majorityElement1(nums);
    }

    public int majorityElement1(int[] nums) {
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            int times = 0;
            for (int j = 0; j < length; j++) {
                if (nums[i] == nums[j] && i != j) {
                    times++;
                }
                if (times >= length / 2) {
                    return nums[j];
                }
            }
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }

        // 找出频率最高的数字
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int majorityElement3(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

    /**
     * 输入: [3,2,3]
     * 输出: 3
     */
    @Test
    public void testCase1() {
        System.out.println(majorityElement(new int[]{3, 2, 3}));
    }

    /**
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     */
    @Test
    public void testCase2() {
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }
}
