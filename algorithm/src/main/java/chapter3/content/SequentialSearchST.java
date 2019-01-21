package chapter3.content;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于链表的无序顺序查找的符号表
 * Created by ZMY on 2017/7/23.
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        // 遍历链表顺序查找
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.value;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        // 不允许插入空值
        if (val == null) {
            delete(key);
            return;
        }
        // 顺序查找要增加的结点是否在链表中,如果在则更新它。
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.value = val;
                return;
            }
        }
        // 若不存在,新建一个结点在表头
        first = new Node(key, val, first);
        N++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        // 保存在队列中
        Queue<Key> queue = new Queue<Key>();
        for (Node current = first; current != null; current = current.next) {
            queue.enqueue(current.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        st.delete("a");
    }
}
