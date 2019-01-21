package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class No145BinaryTreePostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        postorderTraversal(root, result);

        return result;
    }

    private void postorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        postorderTraversal(node.left, result);
        postorderTraversal(node.right, result);
        result.add(node.val);
    }
}
