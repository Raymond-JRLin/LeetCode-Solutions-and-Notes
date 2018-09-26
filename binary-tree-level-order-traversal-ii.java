// 107. Binary Tree Level Order Traversal II
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its bottom-up level order traversal as:
// [
//   [15,7],
//   [9,20],
//   [3]
// ]



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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // return mytry(root);

        return method2(root);
    }

    private List<List<Integer>> method2(TreeNode root) {
        // recursion
        List<List<Integer>> result = new ArrayList<>();
        recursion(root, result, 0);
        return result;
    }
    private void recursion(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null) {
            return;
        }
        if (level >= result.size()) {
            result.add(0, new ArrayList<Integer>());
        }
        result.get(result.size() - 1 - level).add(root.val);
        recursion(root.left, result, level + 1);
        recursion(root.right, result, level + 1);
    }

    private List<List<Integer>> mytry(TreeNode root) {
        // BFS
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                list.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            result.add(0, list);
        }
        return result;
    }
}
