// 104. Maximum Depth of Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, find its maximum depth.
//
// The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given binary tree [3,9,20,null,null,15,7],
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its depth = 3.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // recursion
        // return method1_recursion(root);

        // iterations: BFS
        // return method2_bfs(root);

        // DFS by stack
        return method3_stack(root);
    }
    private int method1_recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(method1_recursion(root.left), method1_recursion(root.right)) + 1;
    }
    private int method2_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int count = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            count++;
        }
        return count;
    }
    private int method3_stack(TreeNode root) {
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> values = new Stack<>();
        nodes.push(root);
        values.push(1);
        int max = Integer.MIN_VALUE;
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.pop();
            int temp = values.pop();
            max = Math.max(max, temp);
            if (curr.left != null) {
                nodes.push(curr.left);
                values.push(temp + 1);
            }
            if (curr.right != null) {
                nodes.push(curr.right);
                values.push(temp + 1);
            }
        }
        return max;
    }
}
