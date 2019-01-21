package leetcode.bitManipulation;

import org.junit.Assert;
import org.junit.Test;

public class No461HammingDistance {
    public int hammingDistance(int x, int y) {

        return hammingDistance2(x, y);
    }

    private int hammingDistance1(int x, int y) {
        int result = x ^ y;
        // 将结果转化为二进制字符串
        String binaryResult = Integer.toBinaryString(result);

        // 统计二进制中1的个数,就是距离
        int count = 0;
        for (char c : binaryResult.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }

    private int hammingDistance2(int x, int y) {
        int result = x ^ y;

        int count = 0;
        while (result > 0) {
            // 与运算
            if ((result & 1) == 1) {
                count++;
            }
            result >>>= 1;
        }
        return count;
    }

    @Test
    public void testCase1() {
        int hammingDistance = hammingDistance(1, 4);
        Assert.assertEquals(2, hammingDistance);
    }
}
