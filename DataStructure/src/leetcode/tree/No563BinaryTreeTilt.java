package leetcode.tree;

import org.junit.Test;

/**
 * 随机一题
 *
 *
 * 给定一个二叉树，计算整个树的坡度。
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 * 整个树的坡度就是其所有节点的坡度之和。
 */
public class No563BinaryTreeTilt {
    public int findTilt(TreeNode root) {
        return findTilt(root,0);
    }

    private int findTilt(TreeNode node, int currentSum) {
        if (node == null) {
            return currentSum;
        }

        int left = findTilt(node.left, currentSum);
        int right = findTilt(node.right, currentSum);

        return Math.abs(left - right);
    }

    /**
     * 输入:
     *          1
     *        /   \
     *       2     3
     * 输出: 1
     * 解释:
     * 结点的坡度 2 : 0
     * 结点的坡度 3 : 0
     * 结点的坡度 1 : |2-3| = 1
     * 树的坡度 : 0 + 0 + 1 = 1
     */
    @Test
    public void testCase1(){
        TreeNode root = new TreeNode(1,new TreeNode(2),new TreeNode(3));

        int result = findTilt(root);
        System.out.println(result);
    }
}
