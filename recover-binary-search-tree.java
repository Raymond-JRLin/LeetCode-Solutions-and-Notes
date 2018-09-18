// 99. Recover Binary Search Tree
// DescriptionHintsSubmissionsDiscussSolution
// Two elements of a binary search tree (BST) are swapped by mistake.
//
// Recover the tree without changing its structure.
//
// Example 1:
//
// Input: [1,3,null,null,2]
//
//    1
//   /
//  3
//   \
//    2
//
// Output: [3,1,null,null,2]
//
//    3
//   /
//  1
//   \
//    2
// Example 2:
//
// Input: [3,1,4,null,null,2]
//
//   3
//  / \
// 1   4
//    /
//   2
//
// Output: [2,1,4,null,null,3]
//
//   2
//  / \
// 1   4
//    /
//   3
// Follow up:
//
// A solution using O(n) space is pretty straight forward.
// Could you devise a constant space solution?


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
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        // mytry(root);

        // method2(root);

        method3(root);
    }

    private void method3(TreeNode root) {
        // Morris traversal: O(N) time and O(1) space
        // ref: https://leetcode.com/problems/recover-binary-search-tree/discuss/32559/Detail-Explain-about-How-Morris-Traversal-Finds-two-Incorrect-Pointer
        // ref: http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
        TreeNode fir = null;
        TreeNode sec = null;
        TreeNode pre = null;
        while (root != null) {
            if (root.left == null) {
                // no left subtree
                if (pre != null && pre.val > root.val) {
                    if (fir == null) {
                        fir = pre;
                        sec = root;
                    } else {
                        sec = root;
                    }
                }
                pre = root;

                root = root.right;
            } else {
                // go left subtree first
                TreeNode temp = root.left;
                // find the predecessor of root
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                }
                if (temp.right != null) {
                    // already threaded with root
                    if (pre != null && pre.val > root.val) {
                        if (fir == null) {
                            fir = pre;
                            sec = root;
                        } else {
                            sec = root;
                        }
                    }
                    pre = root;

                    temp.right = null;
                    root = root.right;
                } else {
                    // thread/connect root and its predecessor
                    temp.right = root;
                    root = root.left;
                }
            }
        }
        // swap first and second
        int temp = fir.val;
        fir.val = sec.val;
        sec.val = temp;
    }

    private void method2(TreeNode root) {
        // use inorder traversal: O(N) time and O(h) space, where h is the height of tree
        // ref: https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
        traverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);

        if (first == null && prev.val > root.val) {
            // System.out.println("first is null and prev-" + prev.val + " is larger than root-" + root.val);
            first = prev;
        }
        if (first != null && prev.val > root.val) {
            // System.out.println("first is not null and prev-" + prev.val + " is larger than root-" + root.val);
            second = root;
        }
        prev = root;

        traverse(root.right);
    }

    private void mytry(TreeNode root) {
        // general: use inorder traversal to see which 2 are swapped, this method can handle every case whatever how many nodes are wrong
        // O(NlogN) time and O(N) space
        List<TreeNode> list = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        inorder(root, list, vals);
        Collections.sort(vals); // cannot go to find this 2 by comparing prev and latter val, since this would get consecutive 2 vals, i.e. 2 examples in problem
        for (int i = 0; i < vals.size(); i++) {
            if (list.get(i).val != vals.get(i)) {
                list.get(i).val = vals.get(i);
            }
        }
    }
    private void inorder(TreeNode root, List<TreeNode> list, List<Integer> vals) {
        if (root == null) {
            return;
        }
        inorder(root.left, list, vals);
        list.add(root);
        vals.add(root.val);
        inorder(root.right, list, vals);
    }
}
