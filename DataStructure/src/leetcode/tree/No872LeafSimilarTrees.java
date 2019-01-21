package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class No872LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafList1 = getLeaf(root1);
        List<Integer> leafList2 = getLeaf(root2);

        leafList1.sort(Comparator.naturalOrder());
        leafList2.sort(Comparator.naturalOrder());

        return leafList1.equals(leafList2);
    }

    private List<Integer> getLeaf(TreeNode node) {
        List<Integer> leafList = new ArrayList<>();
        getLeaf(node, leafList);
        return leafList;
    }

    private void getLeaf(TreeNode node, List<Integer> leafList) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leafList.add(node.val);
            return;
        }

        getLeaf(node.left, leafList);
        getLeaf(node.right, leafList);
    }

    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        List<Integer> leaf = getLeaf(root);
    }
}
