// 199. Binary Tree Right Side View
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
// Example:
//
// Input: [1,2,3,null,5,null,4]
// Output: [1, 3, 4]
// Explanation:
//
//    1            <---
//  /   \
// 2     3         <---
//  \     \
//   5     4       <---
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


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
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // return mytry(root);

        return method2(root);
    }

    private List<Integer> method2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == 0) {
                    result.add(curr.val);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
            }
        }
        return result;
    }

    private List<Integer> mytry(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(root, result, 0);
        return result;
    }
    private void recursion(TreeNode root, List<Integer> result, int level) {
        if (root == null) {
            return;
        }
        if (level >= result.size()) {
            result.add(root.val);
        }
        recursion(root.right, result, level + 1);
        recursion(root.left, result, level + 1);
    }
}
