// 437. Path Sum III
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given a binary tree in which each node contains an integer value.
//
// Find the number of paths that sum to a given value.
//
// The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
//
// The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
//
// Example:
//
// root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//       10
//      /  \
//     5   -3
//    / \    \
//   3   2   11
//  / \   \
// 3  -2   1
//
// Return 3. The paths that sum to 8 are:
//
// 1.  5 -> 3
// 2.  5 -> 2 -> 1
// 3. -3 -> 11
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        // return mytry(root, sum);

        return method2(root, sum);
    }

    private int method2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 用 map 把 prefix sum 和对应的 path 数记录下
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 初始状态， 什么都没走
        int curr = dfs2(root, sum, root.val, map);
        return curr;
    }
    private int dfs2(TreeNode root, int sum, int curr, Map<Integer, Integer> map) {
        // 以 root node 为 root 的 tree 有多少 path 等于 sum
        if (root == null) {
            return 0;
        }
        // 要先拿到一下， 防止下面会覆盖
        int result = map.getOrDefault(curr - sum, 0);

        map.put(curr, map.getOrDefault(curr, 0) + 1);

        if (root.left != null) {
            result += dfs2(root.left, sum, curr + root.left.val, map);
        }
        if (root.right != null) {
            result += dfs2(root.right, sum, curr + root.right.val, map);
        }
        // backtracking
        map.put(curr, map.get(curr) - 1);
        return result;
    }

    private int mytry(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 每个 node 都可以作为起点， 以其为 root 的 tree 去查找
        int leftPathSum = mytry(root.left, sum);
        int rightPathSum = mytry(root.right, sum);
        int curr = dfs(root, sum, root.val);
        return curr + leftPathSum + rightPathSum;
    }
    private int dfs(TreeNode root, int sum, int curr) {
        // 以 root node 为 root 的 tree 有多少 path 等于 sum
        if (root == null) {
            return 0;
        }

        int result = 0;
        if (curr == sum) {
            result++;
        }

        if (root.left != null) {
            result += dfs(root.left, sum, curr + root.left.val);
        }
        if (root.right != null) {
            result += dfs(root.right, sum, curr + root.right.val);
        }
        return result;
    }
}
