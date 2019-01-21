package chapter4.content;

import chapter1.content.Bag;
import chapter1.content.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * 有向图
 * Created by ZMY on 2017/7/30.
 */
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;        // 结点数
    private int E;              // 边数
    private Bag<Integer>[] adj; // 邻接表
    private int[] indegree;     // 结点入度

    // 创建v个结点且没有边的有向图
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < V; v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * 返回结点个数
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
     * 增加一条v->w边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    /**
     * 结点入度
     *
     * @param v
     * @return
     */
    public int indegree(int v) {
        return indegree[v];
    }

    /**
     * 结点出度
     *
     * @param v
     * @return
     */
    public int outdegree(int v) {
        return adj[v].size();
    }

    /**
     * 返回该结点的邻接表
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 有向图取反
     *
     * @return
     */
    public Digraph reverse() {
        Digraph newDigraph = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                addEdge(w, v);
            }
        }
        return newDigraph;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In("/tinyDG.txt");
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }
}
