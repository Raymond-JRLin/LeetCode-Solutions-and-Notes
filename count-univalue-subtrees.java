// 250. Count Univalue Subtrees

// Given a binary tree, count the number of uni-value subtrees.
//
// A Uni-value subtree means all nodes of the subtree have the same value.
//
// Example :
//
// Input:  root = [5,1,5,5,5,null,5]
//
//               5
//              / \
//             1   5
//            / \   \
//           5   5   5
//
// Output: 4


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
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // return mytry(root);

        return method2(root);
    }

    private int method2(TreeNode root) {
        // mytry 的问题在于重复计算， 每次到了 subtree 都会去查整棵 subtree 是否是 uni-value 的， 想要只计算一次， 那么要采用 post order， 即从下面开始， 先看 left child and right child 是否一样， 一样的话才有可能是 uni-value 的树， 然后才需要继续往上查； 因为如果下面的都不一样了， 上面的 subtree 肯定不是 uni-value 的
        result = 0;
        postorder(root);
        return result;
    }
    private boolean postorder(TreeNode root) {
        // D&C
        if (root == null) {
            return true;
        }
        boolean left = postorder(root.left); // check if left subtree is a uni-value tree
        boolean right = postorder(root.right); // check if right subtree is a uni-value tree
        if (left && right) {
            // if left subtree and right subtree are both uni-value tree, then subtree with current root has possibility to be a uni-value tree
            // remove false scenarios
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            result++;
            return true;
        }
        return false;
    }

    int result;
    private int mytry(TreeNode root) {
        // brute force: check every subtree
        result = 0;
        dfs(root);
        return result;
    }
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (isUni(root, root.val)) {
            result++;
        }
        dfs(root.left);
        dfs(root.right);
    }
    private boolean isUni(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        return root.val == val && isUni(root.left, val) && isUni(root.right, val);
    }
}
