// 938. Range Sum of BST
// DescriptionHintsSubmissionsDiscussSolution
//
// Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
//
// The binary search tree is guaranteed to have unique values.
//
//
//
// Example 1:
//
// Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
// Output: 32
//
// Example 2:
//
// Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
// Output: 23
//
//
//
// Note:
//
//     The number of nodes in the tree is at most 10000.
//     The final answer is guaranteed to be less than 2^31.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


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
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        // return mytry(root, L, R);

        return mytry2(root, L, R);
    }

    private int mytry2(TreeNode root, int L, int R) {
        result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                continue;
            }
            if (curr.val <= R && curr.val >= L) {
                result += curr.val;
            }
            if (curr.val > L) {
                queue.offer(curr.left);
            }
            if (curr.val < R) {
                queue.offer(curr.right);
            }
        }
        return result;
    }

    private int mytry(TreeNode root, int L, int R) {
        result = 0;
        recursion(root, L, R);
        return result;
    }
    private int result;
    private void recursion(TreeNode root, int L, int R) {
        if (root == null) {
            return;
        }
        if (root.val > R) {
            recursion(root.left, L, R);
        } else if (root.val < L) {
            recursion(root.right, L, R);
        } else {
            result += root.val;
            recursion(root.left, L, R);
            recursion(root.right, L, R);
        }
    }
}
