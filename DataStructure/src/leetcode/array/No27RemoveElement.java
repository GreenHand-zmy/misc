package leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class No27RemoveElement {
    public int removeElement(int[] nums, int val) {
        return removeElement2(nums, val);
    }

    /**
     * 双指针,题目明确不需要考虑数组顺序
     *
     * @param nums
     * @param val
     * @return
     */
    private int removeElement2(int[] nums, int val) {
        int length = 0;

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[i] != val) {
                i++;
                length++;
            } else {
                nums[i] = nums[j];
                j--;
            }
        }

        return length;
    }

    /**
     * 常规做法,删除元素数组前移
     *
     * @param nums
     * @param val
     * @return
     */
    private int removeElement1(int[] nums, int val) {
        int tail = nums.length;

        while (remove(nums, val)) {
            tail--;
        }

        return tail;
    }

    private boolean remove(int[] data, int val) {
        int length = data.length;
        for (int i = 0; i < length; i++) {
            if (data[i] == val) {
                System.arraycopy(data, i + 1, data, i, length - i - 1);
                data[length - 1] = -1;
                return true;
            }
        }

        return false;
    }

    /**
     * 给定 nums = [3,2,2,3], val = 3,
     * <p>
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void testCase1() {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        int result = removeElement(nums, val);

        Assert.assertEquals(2, result);
    }
}
