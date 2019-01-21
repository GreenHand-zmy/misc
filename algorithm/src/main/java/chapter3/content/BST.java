package chapter3.content;

import chapter1.content.Queue;

/**
 * 基于二叉查找树的符号表
 * Created by ZMY on 2017/7/24.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;  // 二叉查找树根结点

    private class Node {
        private Key key;         // 键
        private Value value;     // 值
        private Node left, right;// 指向子树的链接
        private int N;           // 以该结点为根的子树中的结点总数

        private Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node current) {
        if (current == null) {
            return 0;
        } else {
            return current.N;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node current, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called get() with a null key");
        }
        // 在以current为根结点的子树中查找并返回key所对应的值
        // 如果找不到则返回null
        if (current == null) {
            return null;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            return get(current.left, key);
        } else if (cmp > 0) {
            return get(current.right, key);
        } else {
            return current.value;
        }
    }

    public void put(Key key, Value value) {
        // 查找key,找到则更新它的值,否则为它创建一个新的结点
        root = put(root, key, value);
    }

    private Node put(Node current, Key key, Value value) {
        // 如果key存在于以current为根节点的子树中则更新它的值
        // 否则将以key和value为键值对的新结点插入到该子树中
        if (current == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = put(current.left, key, value);
        } else if (cmp > 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    public Key min() {
        return min(root).key;
    }

    public Node min(Node current) {
        if (current.left == null) {
            return current;
        }
        return (min(current.left));
    }

    public Key max() {
        return max(root).key;
    }

    public Node max(Node current) {
        if (current.right == null) {
            return current;
        }
        return (min(current.right));
    }

    public Key floor(Key key) {
        Node current = floor(root, key);
        if (current == null) {
            return null;
        }
        return current.key;
    }

    private Node floor(Node current, Key key) {
        if (current == null) {
            return null;
        }
        int cmp = key.compareTo(current.key);
        if (cmp == 0) {
            return current;
        }
        if (cmp < 0) {
            return floor(current.left, key);
        }
        Node t = floor(current.right, key);
        if (t != null) {
            return t;
        } else {
            return current;
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        Node current = select(root, k);
        return current.key;
    }

    private Node select(Node current, int k) {
        if (current == null) {
            return null;
        }
        int t = size(current.left);
        if (t > k) {
            return select(current.left, k);
        } else if (t > k) {
            return select(current.right, k - t - 1);
        } else {
            return current;
        }
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(root, key);
    }

    private int rank(Node current, Key key) {
        if (current == null) {
            return 0;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            return rank(current.left, key);
        } else if (cmp > 0) {
            return 1 + size(current.left) + rank(current.right, key);
        } else {
            return size(current.left);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node current) {
        if (current.left == null) {
            return current.right;
        }
        current.left = deleteMin(current.left);
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node current) {
        if (current.right == null) {
            return current.left;
        }
        current.right = deleteMax(current.right);
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node current, Key key) {
        if (current == null) {
            return null;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = delete(current.left, key);
        } else if (cmp > 0) {
            current.right = delete(current.right, key);
        } else {
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            Node t = current;
            current = min(current.right);
            current.right = deleteMin(t.right);
            current.left = t.left;
        }
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    private void print(Node current) {
        // 中序遍历
        if (current == null) {
            return;
        }
        print(current.left);
        System.out.print(current.key + " ");
        print(current.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node current, Queue<Key> queue, Key lo, Key hi) {
        if (current == null) {
            return;
        }
        int cmpLo = lo.compareTo(current.key);
        int cmpHi = hi.compareTo(current.key);
        if (cmpLo < 0) {
            keys(current.left, queue, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            queue.enQueue(current.key);
        }
        if (cmpHi > 0) {
            keys(current.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("S", 0);
        bst.put("E", 1);
        bst.put("A", 2);
        bst.put("R", 3);
        bst.put("C", 4);
        bst.put("H", 5);
        bst.put("E", 6);
        bst.put("X", 7);
        bst.print(bst.root);
    }
}
