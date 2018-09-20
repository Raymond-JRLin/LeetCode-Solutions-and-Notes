// 101. Symmetric Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
//
//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
// But the following [1,2,2,null,3,null,3] is not:
//     1
//    / \
//   2   2
//    \   \
//    3    3
// Note:
// Bonus points if you could solve it both recursively and iteratively.


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

        // recursion: continuously check left and right children
        // return my_try(root.left, root.right);

        // iteration
        return iteration(root);
    }

    private boolean iteration(TreeNode root) {
        if (root.left == null || root.right == null) {
            return root.left == root.right;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null || right == null) {
                if (left != right) {
                    return false;
                }
            }
            if (left.val != right.val) {
                return false;
            }
            if (left.left != null) {
                if (right.right == null) {
                    return false;
                }
                queue.offer(left.left);
                queue.offer(right.right);
            } else {
                if (right.right != null) {
                    return false;
                }
            }
            if (left.right != null) {
                if (right.left == null) {
                    return false;
                }
                queue.offer(left.right);
                queue.offer(right.left);
            } else {
                if (right.left != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean my_try(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return my_try(left.left, right.right) && my_try(left.right, right.left);
    }
}
