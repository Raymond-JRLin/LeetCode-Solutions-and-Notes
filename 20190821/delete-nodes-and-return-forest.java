// 1110. Delete Nodes And Return Forest
// DescriptionHintsSubmissionsDiscussSolution
//
// Given the root of a binary tree, each node in the tree has a distinct value.
//
// After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
//
// Return the roots of the trees in the remaining forest.  You may return the result in any order.
//
//
//
// Example 1:
//
// Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
// Output: [[1,2,null,4],[6],[7]]
//
//
//
// Constraints:
//
//     The number of nodes in the given tree is at most 1000.
//     Each node has a distinct value between 1 and 1000.
//     to_delete.length <= 1000
//     to_delete contains distinct values between 1 and 1000.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        // return mytry(root, to_delete);

        return method2(root, to_delete);
    }

    private List<TreeNode> method2(TreeNode root, int[] toDelete) {
        // mytry 太麻烦了一点， 时间复杂度有点儿高， 应该在递归树的过程中就把所有要 delete 的都找到并删掉， 然后通过递归返回 root
        // O(N) time, where N is the #nodes in root
        Set<Integer> set = new HashSet<>();
        for (int num : toDelete) {
            set.add(num);
        }
        List<TreeNode> result = new ArrayList<>();
        recursion(root, true, result, set);
        return result;
    }
    private TreeNode recursion(TreeNode root, boolean isRoot, List<TreeNode> result, Set<Integer> set) {
        if (root == null) {
            return root;
        }
        boolean toDelete = set.contains(root.val);
        // 把所有 root 且不用删掉的放进来
        if (isRoot && !toDelete) {
            result.add(root);
        }
        // children 是否为新的 root 取决于当前 root 是否要被删掉
        root.left = recursion(root.left, toDelete, result, set);
        root.right = recursion(root.right, toDelete, result, set);
        return toDelete ? null : root;
    }

    private List<TreeNode> mytry(TreeNode root, int[] toDelete) {
        List<TreeNode> list = new LinkedList<>();
        list.add(root);
        for (int num : toDelete) {
            List<TreeNode> next = new LinkedList<>();
            boolean deleted = false;
            for (TreeNode head : list) {
                if (deleted) {
                    next.add(head);
                    continue;
                }
                deleted = delete(head, null, true, num, next);
                if (head.val != num) {
                    next.add(head);
                }
            }
            list = next;
        }
        return list;
    }
    private boolean delete(TreeNode root, TreeNode parent, boolean isLeft, int num, List<TreeNode> list) {
        if (root == null) {
            return false;
        }
        if (root.val == num) {
            if (root.left != null) {
                list.add(root.left);
            }
            if (root.right != null) {
                list.add(root.right);
            }
            if (parent != null) {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            root = null;
            return true;
        }
        return delete(root.left, root, true, num, list) || delete(root.right, root, false, num, list);
    }
}
