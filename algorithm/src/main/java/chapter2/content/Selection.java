package chapter2.content;

/**
 * 选择排序
 * Created by ZMY on 2017/7/20.
 */
public class Selection {
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
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        // 遍历数据
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] data = {5, 4, 3, 2, 1, 3};
        sort(data);
        show(data);
    }
}
