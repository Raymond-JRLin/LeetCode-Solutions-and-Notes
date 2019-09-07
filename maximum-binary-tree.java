// 654. Maximum Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
//
// The root is the maximum number in the array.
// The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
// The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
// Construct the maximum tree by the given array and output the root node of this tree.
//
// Example 1:
// Input: [3,2,1,6,0,5]
// Output: return the tree root node representing the following tree:
//
//       6
//     /   \
//    3     5
//     \    /
//      2  0
//        \
//         1
// Note:
// The size of the given array will be in the range [1,1000].
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length;
        return helper(nums, 0, n - 1);
    }
    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int index = findMax(nums, start, end);
        TreeNode root = new TreeNode(nums[index]);
        root.left = helper(nums, start, index - 1);
        root.right = helper(nums, index + 1, end);
        return root;
    }
    private int findMax(int[] nums, int start, int end) {
        // int max = Integer.MIN_VALUE;
        // int index = -1;
        int index = start;
        for (int i = start; i <= end; i++) {
            // if (nums[i] > max) {
            //     max = nums[i];
            //     index = i;
            // }
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        return index;
    }
}
