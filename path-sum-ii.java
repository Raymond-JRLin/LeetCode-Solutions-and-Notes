// 113. Path Sum II

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
// Return:
//
// [
//    [5,4,11,2],
//    [5,8,4,5]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return Collections.emptyList();
        }

        return mytry(root, sum);
    }

    private List<List<Integer>> mytry(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, sum, result, new ArrayList<>(), 0);
        return result;
    }
    private void dfs(TreeNode root, int target, List<List<Integer>> result, List<Integer> list, int sum) {
        list.add(root.val);
        int curr = sum + root.val;
        // cannot return here if sum is already larger than target, since there're positive and negatives
        if (root.left == null && root.right == null) {
            if (curr == target) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        if (root.left != null) {
            dfs(root.left, target, result, list, curr);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            dfs(root.right, target, result, list, curr);
            list.remove(list.size() - 1);
        }
    }
}
