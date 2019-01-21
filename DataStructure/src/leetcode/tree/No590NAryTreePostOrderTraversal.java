package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No590NAryTreePostOrderTraversal {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();

        postorder(root, result);

        return result;
    }

    private void postorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.children != null) {
            for (Node child : node.children) {
                postorder(child, result);
            }
        }
        result.add(node.val);
    }

    @Test
    public void testCase1() {
        Node root = new Node(1, null);
        root.children = List.of(
                new Node(3, List.of(new Node(5, null)
                        , new Node(6, null))),
                new Node(2, null), new Node(4, null));

        List<Integer> postorder = postorder(root);
    }
}
