package chapter4.content;

import chapter1.content.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * 无向图
 * Created by ZMY on 2017/7/29.
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;        // 顶点数量
    private int E;              // 边的数量
    private Bag<Integer>[] adj;   // 邻接表

    /**
     * 构造一张图
     *
     * @param V
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // 创建邻接表
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();           // 将所有链表初始化为空
        }
    }

    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * 返回结点数
     *
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 返回边数
     *
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * 增加一条边
     *
     * @param v 结点v
     * @param w 结点w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);     // 将w添加到v的链表中
        adj[w].add(v);     // 将v添加到w的链表中
        E++;
    }

    /**
     * 返回一个结点的邻接结点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In("/mediumG.txt");
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
