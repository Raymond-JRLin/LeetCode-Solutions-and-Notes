// 508. Most Frequent Subtree Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
//
// Examples 1
// Input:
//
//   5
//  /  \
// 2   -3
// return [2, -3, 4], since all the values happen only once, return all of them in any order.
// Examples 2
// Input:
//
//   5
//  /  \
// 2   -5
// return [2], since 2 happens twice, however -5 only occur once.
// Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.


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
    public int[] findFrequentTreeSum(TreeNode root) {
//         my try: use DFS to get all subtree sum and use map to record  them, then traverse map to find the sum with max frequency
        return my_try(root);
    }
    private int[] my_try(TreeNode root){
        Map<Integer, Integer> map = new HashMap<>(); // <sum, freq>
        subtreeSum(root, map);
        int maxFreq = 0;
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int sum = entry.getKey();
            int freq = entry.getValue();
            if (freq > maxFreq) {
                maxFreq = freq;
                list = new ArrayList<>();
                list.add(sum);
            } else if (freq == maxFreq) {
                list.add(sum);
            } else {
                continue;
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    private int subtreeSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        //
        int left_sum = subtreeSum(root.left, map);
        int right_sum = subtreeSum(root.right, map);
        int sum = root.val + left_sum + right_sum;
        if (map.containsKey(sum)) {
            map.put(sum, map.get(sum) + 1);
        } else {
            map.put(sum, 1);
        }
        return sum;
    }
}
