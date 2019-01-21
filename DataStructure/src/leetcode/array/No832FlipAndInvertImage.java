package leetcode.array;

import org.junit.Assert;
import org.junit.Test;

public class No832FlipAndInvertImage {
    public int[][] flipAndInvertImage(int[][] A) {

        for (int row = 0; row < A.length; row++) {

            // 逆序
            int right = A[row].length - 1;
            for (int column = 0; column < A[row].length; column++) {
                if (column >= right) {
                    break;
                }
                swap(A[row], column, right--);
            }

            // 反转,将0变1,将1变0
            for (int column = 0; column < A[row].length; column++) {
                if (A[row][column] == 1) {
                    A[row][column] = 0;
                } else if (A[row][column] == 0) {
                    A[row][column] = 1;
                }
            }

        }
        return A;
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Test
    public void testCase1() {
        No832FlipAndInvertImage run = new No832FlipAndInvertImage();
        // [[1,1,0],[1,0,1],[0,0,0]]
        int[][] data = new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] image = run.flipAndInvertImage(data);

        //[[1,0,0],[0,1,0],[1,1,1]]
        int[][] expert = new int[][]{{1, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        for (int row = 0; row < expert.length; row++) {
            for (int column = 0; column < expert[row].length; column++) {
                Assert.assertEquals(image[row][column], expert[row][column]);
            }
        }
    }

    @Test
    public void testCase2() {
        // [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
        No832FlipAndInvertImage run = new No832FlipAndInvertImage();
        int[][] data = new int[][]{{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
        int[][] image = run.flipAndInvertImage(data);

        // [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
        int[][] expert = new int[][]{{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 0, 1, 0}};
        for (int row = 0; row < expert.length; row++) {
            for (int column = 0; column < expert[row].length; column++) {
                Assert.assertEquals(image[row][column], expert[row][column]);
            }
        }
    }
}
