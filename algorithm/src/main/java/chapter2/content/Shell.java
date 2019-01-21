package chapter2.content;

/**
 * 希尔排序
 * Created by ZMY on 2017/7/20.
 */
public class Shell {
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

    /**
     * 插入排序
     * 假设第一个元素是有序的
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int length = a.length;
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;  // 1,4,13,40,121,364,1093,...
        }
        while (h >= 1) {
            // 将数组变有序
            for (int i = h; i < length; i++) {
                // 将a[i]插入到a[i-h],a[i-2h],a[i-3h]...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] data = {5, 4, 3, 2, 1};
        sort(data);
        show(data);
    }
}
