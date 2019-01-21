package chapter4.content;

import chapter1.content.Stack;

/**
 * 有向环的检测
 * Created by ZMY on 2017/8/1.
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;   // 有向环中的所有顶点
    private boolean[] onStack;      // 递归调用的栈上的所有顶点

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.V()];
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        // 查询所有的点是否有环
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked(v)) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        // 递归前入栈
        onStack[v] = true;
        // 设置已访问
        marked[v] = true;
        // 对该结点邻接表遍历
        for (int w : digraph.adj(v)) {
            // 如果已经有环,则不进行遍历下去
            if (hasCycle()) {
                return;
            } else if (!marked(w)) {
                // 记录路径
                edgeTo[w] = v;
                // 没有访问过,则继续递归遍历
                dfs(digraph, w);
            } else if (onStack[w]) {
                // 如果路径往回走,则遇见了环
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        // 递归结束后,在onStack除去
        onStack[v] = false;
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
