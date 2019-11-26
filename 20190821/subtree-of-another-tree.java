// 572. Subtree of Another Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
//
// Example 1:
// Given tree s:
//
//      3
//     / \
//    4   5
//   / \
//  1   2
//
// Given tree t:
//
//    4
//   / \
//  1   2
//
// Return true, because t has the same structure and node values with a subtree of s.
//
// Example 2:
// Given tree s:
//
//      3
//     / \
//    4   5
//   / \
//  1   2
//     /
//    0
//
// Given tree t:
//
//    4
//   / \
//  1   2
//
// Return false.
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }

        // return mytry(s, t);

        return method2(s, t);
    }

    private boolean method2(TreeNode s, TreeNode t) {
        // 或者用 isSameTree 那题来查看每个 node 在 s 中是否能成为 root 与 t 比较
        if (s == null) {
            return false;
        }
        if (isSame(s, t)) {
            return true;
        }
        // 以左右子树重新作为 root， 同 s 比较
        return method2(s.left, t) || method2(s.right, t);
    }
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    private boolean mytry(TreeNode s, TreeNode t) {
        // 有可能有多个相同的 node， e.g. [1, 1], [1]
        List<TreeNode> list = new ArrayList<>();
        find(s, t.val, list);
        for (TreeNode root : list) {
            if (check(root, t)) {
                return true;
            }
        }
        return false;
    }
    private boolean check(TreeNode root, TreeNode t) {
        if (root == null && t != null || root != null && t == null) {
            return false;
        }
        if (root == null && t == null) {
            return true;
        }
        if (root.val != t.val) {
            return false;
        }
        return check(root.left, t.left) && check(root.right, t.right);
    }
    private void find(TreeNode s, int target, List<TreeNode> list) {
        if (s == null) {
            return;
        }
        if (s.val == target) {
            list.add(s);
        }
        find(s.left, target, list);
        find(s.right, target, list);
    }
}
