// 653. Two Sum IV - Input is a BST
// DescriptionHintsSubmissionsDiscussSolution
// Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
//
// Example 1:
// Input:
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7
//
// Target = 9
//
// Output: True
// Example 2:
// Input:
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7
//
// Target = 28
//
// Output: False


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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        return mytry(root, k);
    }

    private boolean mytry(TreeNode root, int k) {
        // similar to HashMap method in normal 2 sum, it doesn't matter if input tree is BST or not
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }
    private boolean dfs(TreeNode root, int target, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        if (set.contains(root.val)) {
            return true;
        }
        set.add(target - root.val);
        return dfs(root.left, target, set) || dfs(root.right, target, set);
    }
}
