// 333. Largest BST Subtree

// Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
//
// Note:
// A subtree must include all of its descendants.
// Here's an example:
//     10
//     / \
//    5  15
//   / \   \
//  1   8   7
// The Largest BST Subtree in this case is the highlighted one.
// The return value is the subtree's size, which is 3.
// Follow up:
// Can you figure out ways to solve it with O(n) time complexity?


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
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // return mytry(root);

        return method2(root);
    }

    private int method2(TreeNode root) {
        // follow-up: O(N) time, so we cannot do top-down, but bottom-up, which means we should record info and backtracking
        return dfs(root).count;
    }
    private Result dfs(TreeNode root) {
        // 这里默认的 min 和 max 需要倒过来， 因为在判断里面是要先判定然后往下层走 （顺的）， 而这里是 bottom-up, 所以要在其中更新
        Result curr = new Result(0, true, Long.MAX_VALUE, Long.MIN_VALUE);
        if (root == null) {
            return curr;
        }
        // divide
        Result left = dfs(root.left);
        Result right = dfs(root.right);
        // update current root's info to backtracking upper level
        curr.min = Math.min(left.min, root.val);
        curr.max = Math.max(right.max, root.val);
        if (left.isBST && right.isBST && root.val < right.min && root.val > left.max) {
            // is BST
            curr.count = left.count + right.count + 1;
            curr.isBST = true;
        } else {
            // is not BST
            curr.isBST = false;
            curr.count = Math.max(left.count, right.count); // still need to carry the max result we currently have
        }
        return curr;
    }
    private class Result {
        private int count;
        private boolean isBST;
        private long min;
        private long max;
        public Result(int c, boolean f, long lower, long upper) {
            count = c;
            isBST = f;
            min = lower;
            max = upper;
        }
    }

    private int mytry(TreeNode root) {
        // brute force: check if each subtree is BST, if so, then count the number of nodes (top-down)
        if (root == null) {
            return 0;
        }
        // D&C
        if (isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            return count(root);
        } else {
            // recursion to current method
            return Math.max(mytry(root.left), mytry(root.right));
        }
    }
    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root.left) + count(root.right) + 1;
    }
    private boolean isBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }
}
