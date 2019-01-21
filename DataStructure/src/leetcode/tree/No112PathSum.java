package leetcode.tree;

import org.junit.Test;

public class No112PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSum(root, sum, 0);
    }

    private boolean hasPathSum(TreeNode node, int targetSum, int sum) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {
            sum += node.val;
            return targetSum == sum;
        }

        sum += node.val;

        return hasPathSum(node.left, targetSum, sum) || hasPathSum(node.right, targetSum, sum);
    }

    /**
     * 给定如下二叉树，以及目标和 sum = 22，
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     */
    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)), null),
                new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));

        System.out.println(hasPathSum(root, 22));
    }
}
