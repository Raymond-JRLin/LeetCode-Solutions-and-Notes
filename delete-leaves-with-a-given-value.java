// 1325. Delete Leaves With a Given Value
//
//     User Accepted: 2761
//     User Tried: 2869
//     Total Accepted: 2824
//     Total Submissions: 3889
//     Difficulty: Medium
//
// Given a binary tree root and an integer target, delete all the leaf nodes with value target.
//
// Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).
//
//
//
// Example 1:
//
// Input: root = [1,2,3,2,null,2,4], target = 2
// Output: [1,null,3,null,4]
// Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
// After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
//
// Example 2:
//
// Input: root = [1,3,3,3,2], target = 3
// Output: [1,3,null,null,2]
//
// Example 3:
//
// Input: root = [1,2,null,2,null,2], target = 2
// Output: [1]
// Explanation: Leaf nodes in green with value (target = 2) are removed at each step.
//
// Example 4:
//
// Input: root = [1,1,1], target = 1
// Output: []
//
// Example 5:
//
// Input: root = [1,2,3], target = 1
// Output: [1,2,3]
//
//
//
// Constraints:
//
//     1 <= target <= 1000
//     Each tree has at most 3000 nodes.
//     Each node's value is between [1, 1000].
//


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
    public TreeNode removeLeafNodes(TreeNode root, int target) {

        // return mytry(root, target);

        return method2(root, target);
    }

    private TreeNode method2(TreeNode root, int target) {
        // 这样的 recursion 更有意义， 我自己写的虽然也对， 但是我觉得没有很好的利用 recursion
        // recursion 的作用在于子问题的求解和原问题是一样的， 对于树来说通常会采用 divide & conquer
        // 那么对于左子树和右子树同样可以用同一个函数来得到应该是的结果， 然后根据左右子树的结果判断是否 root 要做改变
        // 也就是 conquer， 即原问题的求解
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left = method2(root.left, target);
        }
        if (root.right != null) {
            root.right = method2(root.right, target);
        }

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        } else {
            return root;
        }
    }

    private TreeNode mytry(TreeNode root, int target) {
        if (root == null) {
            return root;
        }

        boolean deleteRoot = recursion(root, target);
        if (deleteRoot) {
            return null;
        } else {
            return root;
        }
    }
    private boolean recursion(TreeNode root, int target) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            // leaf
            if (root.val == target) {
                return true;
            } else {
                return false;
            }
        }
        boolean deleteLeft = recursion(root.left, target);
        boolean deleteRight = recursion(root.right, target);
        if (deleteLeft) {
            root.left = null;
        }
        if (deleteRight) {
            root.right = null;
        }
        if (deleteLeft && deleteRight) {
            if (root.val == target) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
