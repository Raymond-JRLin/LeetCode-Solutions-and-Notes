// 124. Binary Tree Maximum Path Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty binary tree, find the maximum path sum.
//
// For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
//
// Example 1:
//
// Input: [1,2,3]
//
//        1
//       / \
//      2   3
//
// Output: 6
// Example 2:
//
// Input: [-10,9,20,null,null,15,7]
//
//    -10
//    / \
//   9  20
//     /  \
//    15   7
//
// Output: 42


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
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 不要被 hard 吓到， 其实仔细思考一下并没有那么难， 关键点就在于它可以从任意一个 node 到任意一个 node， 但是不能重复走。 这么讲可能比较难， 因为没办法从一个点走到任意一个点， 还能得到 path sum。 但是之前做过类似的题就是从 root 出发的 path sum， 那么如果知道某个 root 的从左子树出发的 path sum 和从右子树出发的 path sum， 那么就可以得到当前 root 最大的值 (local max)。 所以其实就是一个 DFS， 就是要用 backtracking， 因为 top-down 的是没办法得到下面的路径和。 所以对于 helper 函数的定义就可以是必须用上当前 root 的最大路径和 (也就是 path sum)， 先不去具体管路径是怎样的， 但不能是左-root-右， 只是单条， 那么对于上层的 root 来说， 最大值的选项就是当前 root 如果两边都是负的， 或者 root 加上 max{左边, 0} and/or 加上 max{右边, 0} (global max)

        return method1(root);
    }

    private int method1(TreeNode root) {
        dfs(root); // we don't need this return value, which is the max path sum of root
        return result;
    }
    private int result = Integer.MIN_VALUE;
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // get max sum of left and right subtree， if it's negative, then don't need to use it
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        int sum = root.val + left + right; // sum of current root
        // update
        result = Math.max(result, sum);
        // return single path sum
        return root.val + Math.max(left, right);
    }
}
