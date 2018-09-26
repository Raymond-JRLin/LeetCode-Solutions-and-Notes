// DescriptionHintsSubmissionsDiscussSolution
// 337. House Robber III
// The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
// Determine the maximum amount of money the thief can rob tonight without alerting the police.
//
// Example 1:
//
// Input: [3,2,3,null,3,null,1]
//
//      3
//     / \
//    2   3
//     \   \
//      3   1
//
// Output: 7
// Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
// Example 2:
//
// Input: [3,4,5,1,3,null,1]
//
//      3
//     / \
//    4   5
//   / \   \
//  1   3   1
//
// Output: 9
// Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.


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
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 第一想到的就是递归的去做， 面对 tree， 我们通常可以使用 divide & conquer
        // return method1_dfs(root);

        // 在 method 1 的递归里有个问题： to obtain rob(root), we need rob(root.left), rob(root.right), rob(root.left.left), rob(root.left.right), rob(root.right.left), rob(root.right.right); but to get rob(root.left), we also need rob(root.left.left), rob(root.left.right), similarly for rob(root.right)。 这就造成了大量的重复计算， 简单地， 我们可以使用 HashMap 来记录
        // return method2_map(root);

        // 更进一步想一想， 造成大量重复计算的原因在于： 每一个节点有可以选择偷或者不偷， 而在上面的递归的过程中， 这个判断没有被继承下去。 从 method 2 也可以看出， 这可以算作一个 DP 的问题， 每一个节点的状态会依赖于它的子节点和孙节点， 这样如果我们在递归的过程中， 把两种状态和他们相对应的最值记录下来， 就可以省掉大量计算， 譬如 root 抢 = root.left + root.right 不抢， root 不抢, 那我们可以考虑 root.left + root.right 抢也可以不抢， 去抢 root.left + root.right 的 children nodes， 这样我们选两个值的 max 即可， 有点儿像 greedy
        return method3_dp(root);
    }

    private int method3_dp(TreeNode root) {
        int[] result = recursion_dp(root);
        return Math.max(result[0], result[1]);
    }
    private int[] recursion_dp(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        // get children node's info
        int[] left = recursion_dp(root.left);
        int[] right = recursion_dp(root.right);

        int[] curr = new int[2]; // [rob current root, do not rob current rob]
        // 1) rob root = no robbing of root.left + root.right
        curr[0] = left[1] + right[1] + root.val;
        // 2) no robbing root = rob/not rob root.left + root.right, attention we can choose rob left and right or just omit them, because the only limitation is "two directly-linked houses" are not broken
        curr[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return curr;
    }

    private int method2_map(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        return recursion_map(root, map);
    }
    private int recursion_map(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int sum = 0;
        if (root.left != null) {
            sum += recursion_map(root.left.left, map) + recursion_map(root.left.right, map);
        }
        if (root.right != null) {
            sum += recursion_map(root.right.left, map) + recursion_map(root.right.right, map);
        }
        int result = Math.max(root.val + sum, recursion_map(root.left, map) + recursion_map(root.right, map));
        map.put(root, result);
        return result;
    }

    private int method1_dfs(TreeNode root) {
        // 考虑 root 能得到的最大值， 1） root 不抢， 抢 root 的 left and right children node, 2） 抢 root， 跳过 left and right children node， 找 root 的 grandchildren node
        if (root == null) {
            return 0;
        }
        int sum = 0;
        // rob root and granchildren node
        if (root.left != null) {
            sum += method1_dfs(root.left.left) + method1_dfs(root.left.right);
        }
        if (root.right != null) {
            sum += method1_dfs(root.right.left) + method1_dfs(root.right.right);
        }
        // compare this way and another of robbing root's left and right children node
        return Math.max(root.val + sum, method1_dfs(root.left) + method1_dfs(root.right));
    }
}
