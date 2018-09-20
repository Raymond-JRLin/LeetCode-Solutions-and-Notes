// 257. Binary Tree Paths
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return all root-to-leaf paths.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Input:
//
//    1
//  /   \
// 2     3
//  \
//   5
//
// Output: ["1->2->5", "1->3"]
//
// Explanation: All root-to-leaf paths are: 1->2->5, 1->3


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
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        return mytry(root);
    }

    private List<String> mytry(TreeNode root) {
        // DFS
        List<String> result = new ArrayList<>();
        dfs(root, result, new StringBuilder());
        return result;
    }
    private void dfs(TreeNode root, List<String> result, StringBuilder sb) {
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            result.add(sb.toString());
            return;
        }

        if (root.left != null) {
            StringBuilder curr = new StringBuilder(sb);
            dfs(root.left, result, curr.append(root.val).append("->"));
        }
        if (root.right != null) {
            StringBuilder curr = new StringBuilder(sb);
            dfs(root.right, result, curr.append(root.val).append("->"));
        }
    }
}
