package chapter4.content;

/**
 * 拓扑排序(只有有向无环图才有拓扑排序)
 * Created by ZMY on 2017/8/1.
 */
public class Topological {
    private Iterable<Integer> order;        // 顶点的拓扑排序

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        // 如果图为有向无环图
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
            // 给出逆后续序列
            order = dfs.reversePost();
        }
    }

    /**
     * digraph是有向无环图吗
     *
     * @return
     */
    public boolean isDAG() {
        return order != null;
    }

    /**
     * 拓扑排序的结果
     *
     * @return
     */
    public Iterable<Integer> order() {
        return order;
    }
}
