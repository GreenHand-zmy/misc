package chapter3.content;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 基于红黑树的符号表
 * Created by ZMY on 2017/7/25.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private boolean isRed(Node current) {
        if (current == null) {
            return false;
        }
        return current.color == RED;
    }

    private int size(Node current) {
        if (current == null) {
            return 0;
        }
        return current.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 红链接左旋
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node current = h.right;
        h.right = current.left;
        current.left = h;
        current.color = h.color;
        h.color = RED;
        current.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return current;
    }

    /**
     * 红链接右旋
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node current = h.left;  //获取指向红链接的子树
        h.left = current.right;
        current.right = h;
        current.color = h.color;
        h.color = RED;
        current.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return null;
    }

    /**
     * 颜色转换
     *
     * @param h
     */
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node current, Key key, Value value) {
        if (current == null) {
            return new Node(key, value, RED, 1);
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = put(current.left, key, value);
        } else if (cmp > 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }
        // 转换颜色
        if (isRed(current.right) && !isRed(current.left)) {
            // 红链接在右,左旋。
            current = rotateLeft(current);
        }
        if (isRed(current.left) && isRed(current.left.left)) {
            // 连续红链接在左,右旋。
            current = rotateRight(current);
        }
        if (isRed(current.left) && isRed(current.right)) {
            // 左右均为红链接,颜色转换。
            flipColors(current);
        }
        current.size = size(current.left) + size(current.right) + 1;
        return current;
    }

    public static void main(String[] args) {


    }

    private class Node {
        private Key key;            // 键
        private Value value;        // 值
        private Node left, right;   // 左右子树
        private int size;              // 这颗子树中的结点总数
        private boolean color;      // 由父节点指向它的链接的颜色

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }
}
