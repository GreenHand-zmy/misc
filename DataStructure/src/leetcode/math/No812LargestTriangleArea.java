package leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 */
public class No812LargestTriangleArea {
    public double largestTriangleArea(int[][] points) {

        double maxX = 0;
        double maxY = 0;
        for (int[] point : points) {
            maxX = maxX < Math.abs(point[0]) * 1.0 ? Math.abs(point[0]) * 1.0 : maxX;
            maxY = maxY < Math.abs(point[1]) * 1.0 ? Math.abs(point[1]) * 1.0 : maxY;
        }

        return (maxX * maxY) / 2;
    }

    /**
     * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
     * 输出: 2
     * 解释:
     * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
     */
    @Test
    public void testCase1() {
        int[][] input = new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}};
        double exceptResult = 2;

        double result = largestTriangleArea(input);
        Assert.assertEquals(exceptResult, result, 10 / Math.pow(10, 6));
    }
}
