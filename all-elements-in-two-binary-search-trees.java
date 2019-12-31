// 1305. All Elements in Two Binary Search Trees
//
//     User Accepted: 2813
//     User Tried: 2880
//     Total Accepted: 2933
//     Total Submissions: 3812
//     Difficulty: Medium
//
// Given two binary search trees root1 and root2.
//
// Return a list containing all the integers from both trees sorted in ascending order.
//
//
//
// Example 1:
//
// Input: root1 = [2,1,4], root2 = [1,0,3]
// Output: [0,1,1,2,3,4]
//
// Example 2:
//
// Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
// Output: [-10,0,0,1,2,5,7,10]
//
// Example 3:
//
// Input: root1 = [], root2 = [5,1,7,0,2]
// Output: [0,1,2,5,7]
//
// Example 4:
//
// Input: root1 = [0,-10,10], root2 = []
// Output: [-10,0,10]
//
// Example 5:
//
// Input: root1 = [1,null,8], root2 = [8,1]
// Output: [1,1,8,8]
//
//
//
// Constraints:
//
//     Each tree has at most 5000 nodes.
//     Each node's value is between [-10^5, 10^5].
//

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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        return mytry(root1, root2);
    }

    private List<Integer> mytry(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        recursion(root1, list1);
        recursion(root2, list2);
        return merge(list1, list2);
    }
    private void recursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        recursion(root.left, list);
        list.add(root.val);
        recursion(root.right, list);
    }
    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() || j < list2.size()) {
            int val1 = i < list1.size() ? list1.get(i) : Integer.MAX_VALUE;
            int val2 = j < list2.size() ? list2.get(j) : Integer.MAX_VALUE;
            if (val1 < val2) {
                result.add(val1);
                i++;
            } else {
                result.add(val2);
                j++;
            }
        }
        return result;
    }
}
