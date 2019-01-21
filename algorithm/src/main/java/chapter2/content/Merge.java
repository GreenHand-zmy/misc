package chapter2.content;

/**
 * 归并排序
 * Created by ZMY on 2017/7/20.
 */
public class Merge {
    private static Comparable[] aux;    // 归并所需的辅助数组

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
     * 判断数据是否已经排序
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k < a.length; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void sort(Comparable[] a) {
//        aux = new Comparable[a.length];// 一次性分配空间
//        sort(a, 0, a.length - 1);
        // 进行lgN次两两归并
        aux = new Comparable[a.length];// 一次性分配空间
        int N = a.length;
        for (int sz = 1; sz < N; sz = sz + sz) {
            // sz子数组大小
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);// 将左半边排序
        sort(a, mid + 1, hi);// 将右半边排序
        merge(a, lo, mid, hi);// 归并结果
    }


    public static void main(String[] args) {
        String[] data = "M E R G E S O R T E X A M P L E".split(" ");
        sort(data);
        show(data);
    }
}
