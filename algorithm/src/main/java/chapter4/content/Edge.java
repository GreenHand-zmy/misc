package chapter4.content;

/**
 * 带权重的边
 * Created by ZMY on 2017/8/2.
 */
public class Edge implements Comparable<Edge> {
    private final int v;            // 顶点之一
    private final int w;            // 另一个顶点
    private final double weight;    // 边的权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public Integer other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        }
        return null;
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) {
            return -1;
        } else if (this.weight() > that.weight()) {
            return +1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
