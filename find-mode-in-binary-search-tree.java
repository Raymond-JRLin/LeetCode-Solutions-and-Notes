// 501. Find Mode in Binary Search Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than or equal to the node's key.
// The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
// Both the left and right subtrees must also be binary search trees.
//
//
// For example:
// Given BST [1,null,2,2],
//
//    1
//     \
//      2
//     /
//    2
//
//
// return [2].
//
// Note: If a tree has more than one mode, you can return them in any order.
//
// Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).


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
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        // return mytry(root);

        return method2(root);
    }

    private int[] method2(TreeNode root) {
        // O(1) space, then we could not store all #count infomation, so just record the max #count, then traverse again with the same process, when the #count == max # count, record this node into final result
        maxCount = 0;
        modeCount = 0; // actuall we don't need maxValue, since 1- it's useless info, 2- they may be several
        currCount = 0;
        currVal = -1;
        inorder(root); // 1st pass to find the max node's value
        currCount = 0;
        currVal = -1;
        result = new int[modeCount]; // initialize before resetting
        modeCount = 0; // reset
        inorder(root); // 2nd pass to find those mode with maxCount found in the 1st pass
        return result;
    }
    private int maxCount;
    private int modeCount;
    private int currCount, currVal;
    private int[] result;
    private void check(int val) {
        if (val != currVal) {
            // a new node
            currVal = val;
            currCount = 1;
        }
        // acumulate count
        currCount++;
        if (currCount > maxCount) {
            // find antoher node with larger count
            maxCount = currCount;
            modeCount = 1; // reset, now there's only 1 node with max count
        } else if(currCount == maxCount) {
            // find another potential mode with same count
            if (result != null) {
                // 2nd pass to get the value with this maxCount
                result[modeCount] = val;
            }
            modeCount++;
        }
    }
    private void inorder(TreeNode root) {
        if (root == null) {
            return ;
        }
        inorder(root.left);
        check(root.val);
        inorder(root.right);
    }

    private int[] mytry(TreeNode root) {
        // O(N) time and O(N) space
        Map<TreeNode, Integer> map = new HashMap<>(); // <node, # node>
        recursion(root, map);
        List<Map.Entry<TreeNode, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<TreeNode, Integer> entry : entries) {
            if (entry.getValue() < max) {
                break;
            }
            max = entry.getValue();
            list.add(entry.getKey().val);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    private void recursion(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return;
        }
        map.put(root, map.getOrDefault(root, 0) + 1);
        recursion(root.left, map);
        recursion(root.right, map);
    }
}
