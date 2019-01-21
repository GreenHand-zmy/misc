package leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

public class No104MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }

        int leftDepth = maxDepth(root.left, depth + 1);
        int rightDepth = maxDepth(root.right, depth + 1);

        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }


    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int exceptDepth = 3;
        int resultDepth = maxDepth(root);

        Assert.assertEquals(exceptDepth, resultDepth);
    }
}
