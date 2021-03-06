package leetcode.array;

import org.junit.Test;

public class XNo867TransposeMatrix {
    public int[][] transpose(int[][] A) {
        int R = A.length, C = A[0].length;
        int[][] ans = new int[C][R];
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                ans[c][r] = A[r][c];
            }
        return ans;
    }

    /**
     * 输入：[[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     */
    @Test
    public void testCase1() {
        int[][] transpose = transpose(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
