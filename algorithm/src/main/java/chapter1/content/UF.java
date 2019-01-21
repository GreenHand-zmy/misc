package chapter1.content;

/**
 * Created by ZMY on 2017/7/20.
 */
public class UF {
    private int[] id;   // 分量id(以触点作为索引)
    private int count;  // 分量数量

    public UF(int N) {
        // 初始化分量id数组
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    /**
     * 判断两结点是否连通
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return (find(p) == find(q));
    }

    /**
     * 找出分量的标识
     *
     * @param p
     * @return
     */
    public int find(int p) {
        return id[p];
    }

    /**
     * 连通两个分量
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        // 将p和q归并到相同的分量中
        int pId = find(p);
        int qId = find(q);
        // 如果p和q已经在相同的分量之中则不需要采取任何行动
        if (pId == qId) {
            return;
        }
        // 将p的分量重命名为q的名称
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        // 每连接两个结点,分量数减1
        count--;
    }

    public static void main(String[] args) {

    }
}
