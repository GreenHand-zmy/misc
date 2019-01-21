import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node {
        E val;
        Node left;
        Node right;

        public Node(E val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E data) {
        root = add(root, data);
    }

    private Node add(Node node, E data) {
        if (node == null) {
            size++;
            return new Node(data);
        }

        if (data.compareTo(node.val) < 0) {
            node.left = add(node.left, data);
        } else if (data.compareTo(node.val) > 0) {
            node.right = add(node.right, data);
        }
        return node;
    }

    public boolean contains(E data) {
        return contains(root, data);
    }

    public boolean contains(Node node, E data) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.val) == 0) {
            return true;
        } else if (data.compareTo(node.val) < 0) {
            return contains(node.left, data);
        } else {
            return contains(node.right, data);
        }
    }

    public void preOrderNR() {
        // 模拟递归系统栈
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            // 访问结点
            Node cur = nodeStack.pop();
            System.out.println(cur.val);

            if (cur.right != null) {
                nodeStack.push(cur.right);
            }
            if (cur.left != null) {
                nodeStack.push(cur.left);
            }
        }

    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

    public void levelOrder() {
        Queue<Node> nodeQueue = new LinkedList<>();

        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {
            Node cur = nodeQueue.remove();

            System.out.println(cur.val);

            if (cur.left != null) {
                nodeQueue.offer(cur.left);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
            }
        }

    }

    public E min() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return min(root).val;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    public E max() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return max(root).val;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }

        return max(node.right);
    }

    public E removeMin() {
        E ret = min();

        root = removeMin(root);

        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = max();

        root = removeMax(root);

        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E data) {
        root = remove(root, data);
    }

    private Node remove(Node node, E data) {
        // todo
        return null;
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = new int[]{5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
                bst.preOrder();
                System.out.println();
                bst.preOrderNR();
                bst.inOrder();
                bst.postOrder();
                System.out.println();
                bst.levelOrder();
//        System.out.println(bst.min());
//        System.out.println(bst.max());
//
//        bst.removeMin();
//        bst.removeMax();


    }
}
