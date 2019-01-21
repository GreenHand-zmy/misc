package chapter2.content;

/**
 * 优先队列
 * Created by ZMY on 2017/7/22.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // 基于堆的完全二叉树
    private int N = 0;  // 存储于pq[1..N]中,pq[0]没有使用

    public MaxPQ() {
        // 创建一个优先队列

    }

    public MaxPQ(int max) {
        // 创建一个最大容量为max的优先队列
        pq = (Key[]) new Comparable[max + 1];
    }

    public MaxPQ(Key[] a) {
        // 用a[]中的元素创建一个优先队列
        pq = (Key[]) new Comparable[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            pq[i + 1] = a[i];
        }
        // TODO: 2017/7/23  加入的元素还未排序
        swim(a.length - 1);
        sink(1);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        // 返回优先队列中的元素的个数
        return N;
    }

    /**
     * 比较大小,当v<w返回true
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 交换i,j
     *
     * @param i
     * @param j
     */
    private void exchange(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 使堆有序化,上浮。
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 使堆有序化,下沉
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k < N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    public void insert(Key v) {
        // 向优先队列中插入一个元素
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        // 返回最大元素
        return pq[1];
    }

    public Key delMax() {
        // 删去最大元素并返回
        Key max = pq[1];    // 从根结点得到最大元素
        exchange(1, N--); // 将其和最后一个结点交换
        pq[N + 1] = null;   // 防止越界
        sink(1);         // 恢复堆的有序性
        return max;
    }

    public void show() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < pq.length; i++) {
            result.append(pq[i] + " ");
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        Integer[] a = {5, 8, 5, 2};
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(a);

        maxPQ.show();
    }

}
