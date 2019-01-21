package leetcode.tree;

import jdk.nashorn.api.tree.NewTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * 计算给定二叉树的所有左叶子之和。
 */
public class XNo404SumOfLeftLeaves {
 /*   public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int sum = 0;
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        return sum;
    }*/

    public int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }

        if (isLeft && root.left == null && root.right == null) {
            return root.val;
        }

        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, false);
    }

    /**
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     */
    @Test
    public void testCase() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int exceptResult = 24;

        int result = sumOfLeftLeaves(root);

        Assert.assertEquals(exceptResult, result);
    }

}
