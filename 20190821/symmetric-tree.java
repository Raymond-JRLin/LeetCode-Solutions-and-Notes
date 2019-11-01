// 101. Symmetric Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
//
//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
//
//
//
// But the following [1,2,2,null,3,null,3] is not:
//
//     1
//    / \
//   2   2
//    \   \
//    3    3
//
//
//
// Note:
// Bonus points if you could solve it both recursively and iteratively.
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        // return mytry(root);

        return mytry2(root);
    }

    private boolean mytry2(TreeNode root) {
        // iteration
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left != null) {
                if (right == null) {
                    return false;
                } else if (left.val != right.val) {
                    return false;
                }
                queue.offer(left.left);
                queue.offer(right.right);
                queue.offer(left.right);
                queue.offer(right.left);
            } else if (right != null) {
                return false;
            }
        }
        return true;
    }

    private boolean mytry(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root.left, root.right);
    }
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null && right != null || left != null && right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
