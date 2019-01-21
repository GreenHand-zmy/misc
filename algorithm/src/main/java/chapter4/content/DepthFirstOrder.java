package chapter4.content;

import chapter1.content.Queue;
import chapter1.content.Stack;

/**
 * 深度优先搜索
 * Created by ZMY on 2017/8/1.
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;         // 所有顶点的前序排列
    private Queue<Integer> post;        // 所有顶点的后序排列
    private Stack<Integer> reversePost; // 所有顶点的逆后续排列

    public DepthFirstOrder(Digraph digraph) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked(v)) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        // 前序:在递归前入队
        pre.enQueue(v);
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked(w)) {
                dfs(digraph, w);
            }
        }
        // 后续:在递归结束后入队
        post.enQueue(v);
        // 逆后续:在递归结束后入栈
        reversePost.push(v);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
