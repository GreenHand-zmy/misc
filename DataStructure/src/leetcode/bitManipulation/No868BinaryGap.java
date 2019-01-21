package leetcode.bitManipulation;

import org.junit.Test;

/**
 * 给定一个正整数 N，找到并返回 N 的二进制表示中两个连续的 1 之间的最长距离。
 * <p>
 * 如果没有两个连续的 1，返回 0 。
 */
public class No868BinaryGap {
    public int binaryGap(int N) {
        int[] binary = new int[32];
        int t = 0;

        for (int i = 0; i < 32; i++) {
            if (((N >> i) & 1) != 0) {
                binary[t++] = i;
            }
        }

        int res = 0;
        for (int i = 0; i < t - 1; i++) {
            res = Math.max(res, binary[i + 1] - binary[i]);
        }
        return res;
    }

    /**
     * 输入：22
     * 输出：2
     * 解释：
     * 22 的二进制是 0b10110 。
     * 在 22 的二进制表示中，有三个 1，组成两对连续的 1 。
     * 第一对连续的 1 中，两个 1 之间的距离为 2 。
     * 第二对连续的 1 中，两个 1 之间的距离为 1 。
     * 答案取两个距离之中最大的，也就是 2 。
     */
    @Test
    public void testCase1() {
        System.out.println(binaryGap(22));
    }
}
