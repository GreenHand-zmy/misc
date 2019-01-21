package chapter4.content;

/**
 * 有向图的深度优先搜索
 * Created by ZMY on 2017/8/1.
 */
public class DirectedDFS {
    private boolean[] marked;

    // 在digraph中找到从start可达的所有顶点
    public DirectedDFS(Digraph digraph, int start) {
        marked = new boolean[digraph.V()];
        dfs(digraph, start);
    }

    // 在digraph中找到从source中的所有订单可达的所有顶点
    public DirectedDFS(Digraph digraph, Iterable<Integer> source) {
        marked = new boolean[digraph.V()];
        for (int v : source) {
            if (!marked(v)) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked(w)) {
                dfs(digraph, w);
            }
        }
    }

    /**
     * 判断结点v是否可达
     *
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return false;
    }

    public static void main(String[] args) {

    }
}
