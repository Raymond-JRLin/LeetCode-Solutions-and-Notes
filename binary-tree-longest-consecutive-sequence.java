// 298. Binary Tree Longest Consecutive Sequence
//
// Given a binary tree, find the length of the longest consecutive sequence path.
//
// The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
//
// Example 1:
//
// Input:
//
//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5
//
// Output: 3
//
// Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
// Example 2:
//
// Input:
//
//    2
//     \
//      3
//     /
//    2
//   /
//  1
//
// Output: 2
//
// Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

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
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // return mytry(root);

        return method2(root);
    }

    private int method2(TreeNode root) {
        // return a int by D&C
        // at least we have root itself, or any node, then length is 1
        return Math.max(recursion(root.left, 1, root.val + 1), recursion(root.right, 1, root.val + 1));
    }
    private int recursion(TreeNode root, int count, int target) {
        if (root == null) {
            return count;
        }
        count = (root.val == target ? count + 1 : 1);
        int left = recursion(root.left, count, root.val + 1);
        int right = recursion(root.right, count, root.val + 1);
        // don't forget to compare count with the max of left and right, count may be smaller when it goes down
        return Math.max(count, Math.max(left, right));
    }

    int result = 0;
    private int mytry(TreeNode root) {
        dfs(root, 1);
        return result;
    }
    private void dfs(TreeNode root, int curr) {
        if (root == null) {
            return;
        }
        result = Math.max(result, curr);
        // attention when it's not consecutive, curr should be 1 again, since the consecutive sequence is broken
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                dfs(root.left, curr + 1);
            } else {
                dfs(root.left, 1);
            }
        }
        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                dfs(root.right, curr + 1);
            } else {
                dfs(root.right, 1);
            }
        }
    }
}
