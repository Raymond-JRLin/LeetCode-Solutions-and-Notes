// 513. Find Bottom Left Tree Value
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, find the leftmost value in the last row of the tree.
//
// Example 1:
// Input:
//
//     2
//    / \
//   1   3
//
// Output:
// 1
// Example 2:
// Input:
//
//         1
//        / \
//       2   3
//      /   / \
//     4   5   6
//        /
//       7
//
// Output:
// 7
// Note: You may assume the tree (i.e., the given root node) is not NULL.


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
    public int findBottomLeftValue(TreeNode root) {

        return method1(root);
    }
    private int method1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = null;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            last = curr;
            if (curr == null) {
                continue;
            }
            if (curr.right != null) {
                last = curr.right;
                queue.offer(curr.right);
            }
            if (curr.left != null) {
                last = curr.left;
                queue.offer(curr.left);
            }

        }
        return last.val;
    }
}
