// 814. Binary Tree Pruning
// DescriptionHintsSubmissionsDiscussSolution
// We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
//
// Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
//
// (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
//
// Example 1:
// Input: [1,null,0,0,1]
// Output: [1,null,0,null,1]
//
// Explanation:
// Only the red nodes satisfy the property "every subtree not containing a 1".
// The diagram on the right represents the answer.
//
//
// Example 2:
// Input: [1,0,1,0,0,0,1]
// Output: [1,null,1,null,1]
//
//
//
// Example 3:
// Input: [1,1,0,1,1,0,1,0]
// Output: [1,1,0,1,1,null,1]
//
//
//
// Note:
//
// The binary tree will have at most 100 nodes.
// The value of each node will only be 0 or 1.


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
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }


        // return mytry(root);

        return method2(root);
    }

    private TreeNode method2(TreeNode root) {
        // don't use RestulType, but just return null if it can be cutted
        if (root == null) {
            return root;
        }
        // modify directly
        root.left = method2(root.left);
        root.right = method2(root.right);

        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        } else {
            return root;
        }
    }

    private TreeNode mytry(TreeNode root) {
        recursion(root);
        return root;
    }
    private ResultType recursion(TreeNode root) {
        if (root == null) {
            return new ResultType(true);
        }

        ResultType left = recursion(root.left);
        ResultType right = recursion(root.right);
        if (left.cut) {
            root.left = null;
        }
        if (right.cut) {
            root.right = null;
        }
        if (left.cut && right.cut && root.val == 0) {
            return new ResultType(true);
        } else {
            return new ResultType(false);
        }
    }
    private class ResultType {
        public boolean cut;
        public ResultType(boolean cut) {
            this.cut = cut;
        }
    }
}
