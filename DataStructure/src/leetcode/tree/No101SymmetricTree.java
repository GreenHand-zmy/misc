package leetcode.tree;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class No101SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }

        // 如果右子树不为空,翻转右子树
        if (root.right != null) {
            invertTree(root.right);
        }

        // 判断左右子树是否相等
        return isSameTree(root.left, root.right);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    private void invertTree(TreeNode node) {
        if (node == null) {
            return;
        }

        invertTree(node.left);
        invertTree(node.right);

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}
