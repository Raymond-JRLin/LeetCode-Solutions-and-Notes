// 515. Find Largest Value in Each Tree Row
// DescriptionHintsSubmissionsDiscussSolution
// You need to find the largest value in each row of a binary tree.
//
// Example:
// Input:
//
//           1
//          / \
//         3   2
//        / \   \
//       5   3   9
//
// Output: [1, 3, 9]
// Seen this question in a real interview before?
// Difficulty:Medium


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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

//         method 1: use BFS to traverse level by level
        // return method1_BFS(root);

//         method 2: use recursion to search each level to find the max in such depth
        return method2(root);
    }
    private List<Integer> method1_BFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            // traverse each layer
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                int temp = curr.val;
                max = Math.max(max, temp);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            result.add(max);
        }
        return result;
    }

//     ref: https://discuss.leetcode.com/topic/79178/9ms-java-dfs-solution
    private List<Integer> method2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(result, root, 0);
        return result;
    }
    private void recursion(List<Integer> result, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // divide
        if (result.size() == depth) {
            result.add(root.val);
        } else {
            // we already have a value for this level, we compare existed and current TreeNode and get a larger one
            result.set(depth, Math.max(result.get(depth), root.val));
        }
        // conque
        recursion(result, root.left, depth + 1);
        recursion(result, root.right, depth + 1);
    }
}
