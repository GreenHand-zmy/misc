package chapter2.content;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 * Created by ZMY on 2017/7/22.
 */
public class Quick {
    /**
     * 展示数据
     *
     * @param a
     */
    private static void show(Comparable[] a) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            result.append(a[i] + " ");
        }
        System.out.println(result);
    }

    /**
     * 比较大小,当v<w返回true
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换i,j
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo..i-1],a[i],a[i+1..hi]
        int i = lo, j = hi + 1;     // 左右扫描指针
        Comparable v = a[lo];       // 切分元素
        while (true) {
            // 扫描左右,检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(a, i, j);
        }
        exchange(a, lo, j);     // 将V = a[j]放入正确的位置
        return j;               // a[lo..j-1] <= a[j] <= a[j+1..hi]达成
    }

    public static void main(String[] args) {
        Integer[] data = {7, 6, 5, 4, 3, 2, 1};
        sort(data);
        show(data);

    }
}
