package leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 */
public class No48RotateImage {
    public void rotate(int[][] matrix) {
        int[][] clone = new int[matrix.length][matrix.length];
        // 二维数组拷贝
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, clone[i], 0, matrix[i].length);
        }

        int column = clone.length - 1;
        for (int[] aClone : clone) {
            for (int j = 0; j < clone.length; j++) {
                matrix[j][column] = aClone[j];
            }
            column--;
        }
    }

    /**
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     */
    @Test
    public void testCase1() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
