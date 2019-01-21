package chapter4.content;

import chapter1.content.Queue;
import chapter1.content.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 广度优先搜索
 * Created by ZMY on 2017/7/30.
 */
public class BreadthFirstPaths {
    private boolean[] marked;   // 到达该顶点的最短路径已知吗？
    private int[] edgeTo;       // 到达该顶点的已知路径上的最后一个结点
    private final int start;

    // 广度优先搜索,获取结点start能到达的所有路径
    public BreadthFirstPaths(Graph graph, int start) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.start = start;
        bfs(graph, start);
    }

    // 广度优先搜索
    private void bfs(Graph graph, int start) {
        Queue<Integer> queue = new Queue<>();
        marked[start] = true;                // 标记起点
        queue.enQueue(start);                // 将它加入队列
        while (!queue.isEmpty()) {           // 队不为空
            int current = queue.deQueue();   // 从队列中删去下一个结点
            for (int adjoin : graph.adj(current)) {
                if (!marked[adjoin]) {
                    edgeTo[adjoin] = current;
                    marked[adjoin] = true;
                    queue.enQueue(adjoin);
                }
            }
        }
    }

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
        String s = "";
        int length = s.length();
        System.out.println(length);
    }
}
