// 112. Path Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given the below binary tree and sum = 22,
//
//       5
//      / \
//     4   8
//    /   / \
//   11  13  4
//  /  \      \
// 7    2      1
// return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return mytry(root, sum);
    }

    private boolean mytry(TreeNode root, int sum) {
        return dfs(root, sum, 0);
    }
    private boolean dfs(TreeNode root, int sum, int curr) {
        curr += root.val;
        if (root.left == null && root.right == null) {
            if (curr == sum) {
                return true;
            } else {
                return false;
            }
        }
        // System.out.println("cur node is " + root.val + " , and curr sum is " + curr);
        // we cannot compare values here like curr >= sum return false, since the val of nodes may be negative
        if (root.left == null) {
            return dfs(root.right, sum, curr);
        } else if (root.right == null) {
            return dfs(root.left, sum, curr);
        } else {
            return dfs(root.left, sum, curr) || dfs(root.right, sum, curr);
        }
    }
}
