package leetcode.array;

import java.util.Arrays;

/**
 * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
 * <p>
 * 你可以返回满足此条件的任何数组作为答案。
 */
public class No905SortArrayByParity {
    public int[] sortArrayByParity(int[] A) {
        return sortArrayByParity1(A);
    }

    // 自定义排序
    private int[] sortArrayByParity1(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }

        Arrays.sort(B, (o1, o2) -> Integer.compare(o1 % 2, o2 % 2));

        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }
        return A;
    }

    // 两次遍历
    private int[] sortArrayByParity2(int[] A) {
        int[] res = new int[A.length];
        int p = 0;

        for (int i = 0; i < res.length; i++) {
            if (A[i] % 2 == 0) {
                res[p++] = A[i];
            }
        }

        for (int i = 0; i < res.length; i++) {
            if (A[i] % 2 == 1) {
                res[p++] = A[i];
            }
        }

        return res;
    }
}
