// 965. Univalued Binary Tree
// User Accepted: 1941
// User Tried: 1978
// Total Accepted: 1976
// Total Submissions: 2511
// Difficulty: Easy
// A binary tree is univalued if every node in the tree has the same value.
//
// Return true if and only if the given tree is univalued.
//
//
//
// Example 1:
//
//
// Input: [1,1,1,1,1,null,1]
// Output: true
// Example 2:
//
//
// Input: [2,2,2,5,2]
// Output: false
//
//
// Note:
//
// The number of nodes in the given tree will be in the range [1, 100].
// Each node's value will be an integer in the range [0, 99].


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
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        return mytry(root);
    }

    private boolean mytry(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        return mytry(root.left) && mytry(root.right);
    }
}
