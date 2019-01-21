package leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No94BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        return inorderTraversal(data, root);
    }

    private List<Integer> inorderTraversal(List<Integer> data, TreeNode node) {
        if (node == null) {
            return data;
        }
        inorderTraversal(data, node.left);
        data.add(node.val);
        inorderTraversal(data, node.right);

        return data;
    }

    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> exceptResult = List.of(1, 3, 2);
        List<Integer> result = inorderTraversal(root);
        Assert.assertEquals(exceptResult, result);
    }
}

