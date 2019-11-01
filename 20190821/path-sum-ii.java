// 113. Path Sum II
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given the below binary tree and sum = 22,
//
//       5
//      / \
//     4   8
//    /   / \
//   11  13  4
//  /  \    / \
// 7    2  5   1
//
// Return:
//
// [
//    [5,4,11,2],
//    [5,8,4,5]
// ]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return Collections.emptyList();
        }

        // return mytry(root, sum);

        return mytry2(root, sum);
    }
    private List<List<Integer>> mytry2(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs2(root, sum, 0, result, new ArrayList<>());
        return result;
    }
    private void dfs2(TreeNode root, int sum, int curr, List<List<Integer>> result, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        curr += root.val;

        if (root.left == null && root.right == null) {
            if (curr == sum) {
                result.add(new ArrayList<>(list));

            }
            // 因为先加了 node 进来， 然后到 leaf 就回溯， 此时要 backtracking 一下
            list.remove(list.size() - 1);
            return;
        }

        dfs2(root.left, sum, curr, result, list);
        dfs2(root.right, sum, curr, result, list);
        curr -= root.val;
        list.remove(list.size() - 1);
    }

    private List<List<Integer>> mytry(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        dfs(root, sum, root.val, result, list);
        return result;
    }
    private void dfs(TreeNode root, int sum, int curr, List<List<Integer>> result, List<Integer> list) {
        if (root.left == null && root.right == null) {
            if (curr == sum) {
                result.add(new ArrayList<>(list));
                return;
            }
            return;
        }
        if (root.left != null) {
            list.add(root.left.val);
            dfs(root.left, sum, curr + root.left.val, result, list);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            list.add(root.right.val);
            dfs(root.right, sum, curr + root.right.val, result, list);
            list.remove(list.size() - 1);
        }
    }
}
