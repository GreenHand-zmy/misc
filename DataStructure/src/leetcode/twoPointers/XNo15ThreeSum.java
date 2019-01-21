package leetcode.twoPointers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class XNo15ThreeSum {
    class Triad {
        private List<Integer> nums = new ArrayList<>(3);

        public Triad(int num1, int num2, int num3) {
            nums.add(num1);
            nums.add(num2);
            nums.add(num3);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triad triad = (Triad) o;
            return triad.nums.contains(nums.get(0)) && triad.nums.contains(nums.get(1)) && triad.nums.contains(nums.get(2));
        }

        @Override
        public int hashCode() {
            return nums.get(0) + nums.get(1) + nums.get(2);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Triad> set = new HashSet<>();

        if (nums.length < 3) {
            return result;
        }

        int point1 = 0, point2 = 1;
        while (point2 < nums.length - 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[point1] + nums[point2] + nums[i] == 0) {
                    set.add(new Triad(nums[point1], nums[point2], nums[i]));
                }
            }
            point1++;
            point2++;
        }

        for (Triad triad : set) {
            List<Integer> subList = new ArrayList<>();
            subList.add(triad.nums.get(0));
            subList.add(triad.nums.get(1));
            subList.add(triad.nums.get(2));

            result.add(subList);
        }

        return result;
    }

    @Test
    public void testCase1() {
        /*int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));*/

        Triad triad1 = new Triad(0, 1, -1);
        Triad triad2 = new Triad(-1, 0, 1);

        System.out.println(triad1.equals(triad2));
        System.out.println(triad1.hashCode());
        System.out.println(triad2.hashCode());
    }


}
