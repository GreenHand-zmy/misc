package chapter4.content;

import chapter1.content.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 深度优先搜索
 * Created by ZMY on 2017/7/30.
 */
public class DepthFirstPaths {
    private boolean[] marked;   // 判断该顶点是否已经访问过
    private int[] edgeTo;       // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int start;        // 起点

    public DepthFirstPaths(Graph graph, int start) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.start = start;
        dfs(graph, start);
    }

    // 深度优先搜索
    private void dfs(Graph graph, int current) {
        // 首次访问,设置为已访问。
        marked[current] = true;
        for (int v : graph.adj(current)) {
            // 访问当前结点的邻接结点
            if (!marked[v]) {
                // 如果当前结点没有被访问过,记录它的父结点
                edgeTo[v] = current;
                // 如果当前邻接结点未被访问过,则先访问该结点
                dfs(graph, v);
            }
        }
    }

    /**
     * 判断是否有路径到达v
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 给出结点到达结点v的路径
     *
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        // 因为给定的结点为路径末尾,所有要顺序给出路径头部到尾部,需要用到栈
        Stack<Integer> path = new Stack<>();
        for (int current = v; current != start; current = edgeTo[current]) {
            // 将路径上结点加入栈(除去路径头)
            path.push(current);
        }
        // 添加路劲头
        path.push(start);
        return path;
    }

    public static void main(String[] args) {
        In in = new In("/tinyCG.txt");
        Graph G = new Graph(in);
        int s = Integer.parseInt("0");
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}


