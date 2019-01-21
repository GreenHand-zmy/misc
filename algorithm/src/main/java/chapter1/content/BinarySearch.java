package chapter1.content;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.io.IOException;
import java.util.Arrays;

/**
 * 第一个程序二分搜索
 * Created by ZMY on 2017/7/15.
 */
public class BinarySearch {
    /**
     * @param key 要查找的值
     * @param a   查找源
     * @return
     */
    public static int rank(int key, int[] a) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // 如果值小于中间的值，则值一点在左边。
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {// 如果值大于中间的值，则值一点在右边
                low = mid + 1;
            } else {// 如果值等于中间值则返回中间值的位置
                return mid;
            }
        }
        // 程序执行到这里说明没有找到该值
        return -1;
    }

    public static void main(String[] args) throws IOException {
        /*In in = new In("/largeW.txt");
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);
        int rank = rank(761071, whitelist);
        System.out.println(rank);*/

    }
}
