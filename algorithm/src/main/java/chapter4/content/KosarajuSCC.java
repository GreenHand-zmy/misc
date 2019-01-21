package chapter4.content;

import sun.awt.geom.AreaOp;

/**
 * Kosaraju算法求强连通分量
 * <p>
 * Created by ZMY on 2017/8/1.
 */
public class KosarajuSCC {
    private boolean[] marked;   // 已访问过的顶点
    private int[] id;           // 强连通分量的标识符
    private int count;          // 强连通分量的数量

    public KosarajuSCC(Digraph digraph) {
        marked = new boolean[digraph.V()];
        id = new int[digraph.V()];
        DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(digraph, s);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
