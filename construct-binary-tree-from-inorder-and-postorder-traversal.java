// 106. Construct Binary Tree from Inorder and Postorder Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given inorder and postorder traversal of a tree, construct the binary tree.
//
// Note:
// You may assume that duplicates do not exist in the tree.
//
// For example, given
//
// inorder = [9,3,15,20,7]
// postorder = [9,15,7,20,3]
// Return the following binary tree:
//
//     3
//    / \
//   9  20
//     /  \
//    15   7


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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        return mytry(inorder, postorder);
    }

    private TreeNode mytry(int[] inorder, int[] postorder) {
        // similar to construct binary tree from inorder and preorder traversal, just modify it from preorder to postorder
        // 后序从右往左依次是先右子树， 再左子树的 root
        // we can also pre-record index and values of inorder in a map
        return build(postorder, inorder, postorder.length - 1, 0, inorder.length - 1);
    }
    private TreeNode build(int[] postorder, int[] inorder, int start, int in_start, int in_end) {
        // start is the current root index in preorder
        if (start < 0 || in_start > in_end) {
            return null;
        }
        int value = postorder[start];
        int index = findIndex(inorder, value);
        TreeNode root = new TreeNode(value);
        // start - 1 is next index in postorder, which is root of right substree
        root.right = build(postorder, inorder, start - 1, index + 1, in_end);
        // we need to count how many nodes are in the right subtree from inorder, then get the start (root) index of left subtree for postorder, # = (int_end - index), left_start_index = start - # - 1
        root.left = build(postorder, inorder, start - (in_end - index) - 1, in_start, index - 1);
        return root;
    }
    private int findIndex(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
