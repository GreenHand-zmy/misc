package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No589NAryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();

        preorder(root, res);

        return res;
    }

    private void preorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }

        list.add(node.val);

        if (node.children != null) {
            for (Node child : node.children) {
                preorder(child, list);
            }
        }
    }

    @Test
    public void testCase1() {
        Node root = new Node(1, List.of(new Node(3,
                        List.of(new Node(5, null), new Node(6, null))),
                new Node(2, null), new Node(4, null)));

        List<Integer> result = preorder(root);
    }
}
