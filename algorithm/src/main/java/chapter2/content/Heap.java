package chapter2.content;

/**
 * 堆排序
 * Created by ZMY on 2017/7/29.
 */
public class Heap {
    /**
     * 比较大小,当v<w返回true
     *
     * @param i
     * @param j
     * @return
     */
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    /**
     * 交换i,j
     *
     * @param i
     * @param j
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
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

    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exchange(a, k, j);
            k = j;
        }
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        while (n > 1) {
            exchange(a, 1, n--);
            sink(a, 1, n);
        }
    }

    public static void main(String[] args) {
        Integer[] data = {7, 6, 5, 4, 3, 2, 1};
        sort(data);
        show(data);
    }
}
