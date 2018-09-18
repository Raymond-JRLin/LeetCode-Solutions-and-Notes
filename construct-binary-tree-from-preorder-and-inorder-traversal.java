// 105. Construct Binary Tree from Preorder and Inorder Traversal

// Given preorder and inorder traversal of a tree, construct the binary tree.
//
// Note:
// You may assume that duplicates do not exist in the tree.
//
// For example, given
//
// preorder = [3,9,20,15,7]
// inorder = [9,3,15,20,7]
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // return mytry(preorder, inorder);

        return method2(preorder, inorder);
    }

    private TreeNode method2(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]), cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                s.push(cur);
                cur = cur.left;
            } else {
                j++;
                while (!s.empty() && s.peek().val == inorder[j]) {
                    cur = s.pop();
                    j++;
                }
                cur = cur.right = new TreeNode(preorder[i]);
            }
        }
        return root;
    }

    private TreeNode mytry(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, 0, inorder.length - 1);
    }
    private TreeNode build(int[] preorder, int[] inorder, int start, int in_start, int in_end) {
        // start is the current root index in preorder
        if (start >= preorder.length || in_start > in_end) {
            return null;
        }
        int value = preorder[start];
        int index = findIndex(inorder, value);
        TreeNode root = new TreeNode(value);
        // start + 1 is next index in preorder, which is root of left substree
        root.left = build(preorder, inorder, start + 1, in_start, index - 1);
        // we need to count how many nodes are in the left subtree from inorder, then get the start (root) index of right subtree for preoder, # = (index - 1 - in_start + 1), right_start_index = start + # + 1
        root.right = build(preorder, inorder, start + (index - in_start) + 1, index + 1, in_end);
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
