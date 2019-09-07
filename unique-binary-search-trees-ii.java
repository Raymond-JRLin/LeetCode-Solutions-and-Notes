// 95. Unique Binary Search Trees II
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
//
// Example:
//
// Input: 3
// Output:
// [
//   [1,null,3,2],
//   [3,2,null,1],
//   [3,1,null,null,2],
//   [2,1,3],
//   [1,null,2,null,3]
// ]
// Explanation:
// The above output corresponds to the 5 unique BST's shown below:
//
//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3
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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }

        return method1(n);
    }

    private List<TreeNode> method1(int n) {
        // 在 I 中， 我们分别去找左边和右边， 然后相乘， 那么在这里需要所有的具体组合， 那么就要改成 2 for loop 去得到所有的组合
        // 还要注意一点， 返回的一个 list， 而不是 List<List<>>, 所以里面加入的应该是 root
        return recursion(1, n);
    }
    private List<TreeNode> recursion(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        if (start == end) {
            result.add(new TreeNode(start));
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = recursion(start, i - 1);
            List<TreeNode> rights = recursion(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i); // root
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
