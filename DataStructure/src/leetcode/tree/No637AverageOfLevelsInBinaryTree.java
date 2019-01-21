package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No637AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> result = new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            double sum = 0;
            int count = 0;
            Queue<TreeNode> temp = new LinkedList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();

                sum += node.val;
                count++;

                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            queue = temp;
            result.add(sum / count);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
